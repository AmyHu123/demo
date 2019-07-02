pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('package') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }

        stage('build') {
            steps {
            checkout scm

            def customImage = docker.build("my-image:${env.BUILD_ID}")

           customImage.push()
           }
        }
    }
}