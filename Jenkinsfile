pipeline {
        agent any

        environment {
                HARBOR_CREDS = credentials('jenkins-harbor')
                GIT_TAG = sh(returnStdout: true,script: 'git describe --tags').trim()
            }

        parameters {
                string(name: 'HARBOR_HOST', defaultValue: 'http://harbor.olavoice.com', description: 'harbor仓库地址')
                string(name: 'DOCKER_IMAGE', defaultValue: 'demo-master', description: 'docker镜像名')
            }

   stages {
           stage('Maven Build') {
               when { expression { env.GIT_TAG != null } }
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
               when {
                   allOf {
                       expression { env.GIT_TAG != null }
                   }
               }
               agent any
               steps {
                   unstash 'demo'
                   sh "docker login -u ${HARBOR_CREDS_USR} -p ${HARBOR_CREDS_PSW} ${params.HARBOR_HOST}"
                   sh "docker build --build-arg JAR_FILE=`ls target/*.jar |cut -d '/' -f2` -t ${params.HARBOR_HOST}/${params.DOCKER_IMAGE}:${GIT_TAG} ."
                   sh "docker push ${params.HARBOR_HOST}/${params.DOCKER_IMAGE}:${GIT_TAG}"
                   sh "docker rmi ${params.HARBOR_HOST}/${params.DOCKER_IMAGE}:${GIT_TAG}"
               }

           }
       }
 }