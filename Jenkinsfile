pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\Program Files\\apache-maven-3.9.10'
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-24'
        PATH = "${env.JAVA_HOME}\\bin;${env.MAVEN_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            // Publish TestNG reports (Maven Surefire generates them in XML format)
            junit 'target\\surefire-reports\\*.xml'
        }
    }
}
