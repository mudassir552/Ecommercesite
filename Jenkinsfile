pipeline {
    agent any  // Runs on any available agent (in your case, the same server)

    environment {
        PROJECT_PATH = "/opt/app"  // Path to your project on the Jenkins server
    }

    stages {
        stage('Checkout') {
            steps {
             
                git branch: 'master', url: 'https://github.com/mudassir552/Ecommercesite.git'
            }
        }

        stage('Build JAR') {
            steps {
                echo 'Building the JAR file'
                dir("${PROJECT_PATH}") {
                    // Run Maven build to package the app
                    sh 'mvn  package -DskipTests'
                }
            }
        }

        stage('Deploy to Server') {
            steps {
                echo 'Deploying the JAR file locally'
                
              sh 'cd ${PROJECT_PATH}/target && java -jar Financeapp-0.0.1-SNAPSHOT.jar --server.port=8081 &'
              
            }
        }
    }

    post {
        success {
            echo 'Deployment to local server successful!'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}
