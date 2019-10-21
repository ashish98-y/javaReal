pipeline{
    agent any
    environment{
         MVN = tool (name: 'maven-3', type: 'maven')
    }
    stages{
        stage("checkout"){
            steps{
                git 'https://github.com/ashish98-y/javaReal.git'
            }
        }
        stage("code analysis"){
            steps{
        sh "${MVN}/bin/mvn sonar:sonar \
  -Dsonar.host.url=http://3.16.78.0:9000 \
  -Dsonar.login=ada77ee49b2f93358ffabc3ade0baf6d51fbea8d"
            }}
        stage("build mvn"){
            steps{
                sh "${MVN}/bin/mvn clean package"
            }
        }
        stage("build container"){
            steps{
                sh "docker build --tag 98ashish/java-app:${BUILD_NUMBER} ."
            }
        }
        stage("push container"){
            steps{
                withCredentials([string(credentialsId: 'dockercred', variable: 'dockerpwd')]) {
                    sh "docker login -u 98ashish -p ${dockerpwd}"
                    sh "docker push 98ashish/java-app:${BUILD_NUMBER}"
                }
            }
        }
        stage("deploy"){
            steps{
                sh label: '', script: 'ansible-playbook -i dev.inventory javaapp.yml'
             }
        }
    }
}
