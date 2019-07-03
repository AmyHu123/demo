pipeline {
        agent any

        environment {
                HARBOR_CREDS = credentials('jenkins-harbor')
                K8S_CONFIG = credentials('jenkins-k8s-config')
            }

        parameters {
                string(name: 'HARBOR_HOST', defaultValue: 'harbor.olavoice.com', description: 'harbor仓库地址')
                string(name: 'DOCKER_IMAGE', defaultValue: 'demo-master', description: 'docker镜像名')
                string(name: 'APP_NAME', defaultValue: 'demo-master', description: 'k8s中标签名')
                string(name: 'K8S_NAMESPACE', defaultValue: 'default', description: 'k8s的namespace名称')
            }

   stages {
           stage('Maven Build') {

               agent {
                   docker {
                       image 'maven:3-jdk-8-alpine'
                       args '-v $HOME/.m2:/root/.m2'
                   }
               }
               steps {
                   sh 'mvn clean package -Dfile.encoding=UTF-8 -DskipTests=true'
                   stash includes: 'target/*.jar', name: 'demo'
               }

           }
           stage('Docker Build') {

               agent any
               steps {
                   unstash 'demo'
                   sh "docker login -u ${HARBOR_CREDS_USR} -p ${HARBOR_CREDS_PSW} ${params.HARBOR_HOST}"
                   sh "docker build --build-arg JAR_FILE=`ls target/*.jar |cut -d '/' -f2` -t ${params.HARBOR_HOST}/test/${params.DOCKER_IMAGE}:1.0 ."
                   sh "docker push ${params.HARBOR_HOST}/test/${params.DOCKER_IMAGE}:1.0"
                   sh "docker rmi ${params.HARBOR_HOST}/test/${params.DOCKER_IMAGE}:1.0"
               }

           }

           stage('Deploy') {
                       agent  any
                       steps {

                           sh "echo ${K8S_CONFIG} | base64 -d > ~/.kube/config"
                           sh "sed -e 's#{IMAGE_URL}#${params.HARBOR_HOST}/test/${params.DOCKER_IMAGE}#g;s#{IMAGE_TAG}#1.0#g;s#{APP_NAME}#${params.APP_NAME}#g' k8s-deployment.tpl > k8s-deployment.yml"
                           sh "kubectl apply -f k8s-deployment.yml --namespace=${params.K8S_NAMESPACE}"
                       }

                   }

               }
       }