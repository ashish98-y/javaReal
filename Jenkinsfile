pipeline{
    agent any
    mvn = tool (name: 'maven-3', type: 'maven') + '/bin/mvn'
    stages{
        stage("checkout"){
            steps{
                git 'https://github.com/ashish98-y/javaReal.git'
            }
        }
        stage("build mvn"){
            steps{
                sh "${mvn} clean package"
            }
        }
        stage("build container"){
            steps{
                sh "docker build --tag 98ashish/java-app:${BUILD_NUMBER} ."
            }
        }
        stage("push container"){
            withCredentials([string(credentialsId: 'dockercred', variable: 'dockerpwd')]) {
                sh "docker login -u 98ashish -p ${dockerpwd}"
                sh "docker push 98ashish/java-app:1.0"
            }
        }
        stage("deploy"){
            steps{
                sh label: '', script: 'ansible-playbook -i dev.inventory javaapp.yml'
             }
        }
    }
}
