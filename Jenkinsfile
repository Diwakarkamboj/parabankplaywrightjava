pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\Program Files\\apache-maven-3.9.10'
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-11'
        PATH = "${JAVA_HOME}\\bin;${MAVEN_HOME}\\bin;${env.PATH}"
    }

    parameters {
        string(name: 'BROWSER', defaultValue: 'chrome', description: 'Browser to run tests on')
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // If Maven wrapper is available, use it
                script {
                    if (fileExists('mvnw.cmd')) {
                        bat ".\\mvnw.cmd clean test -Dbrowser=${params.BROWSER}"
                    } else {
                        bat "mvn clean test -Dbrowser=${params.BROWSER}"
                    }
                }
            }
        }
    }

    post {
        always {
            // Publish TestNG reports
            junit 'target\\surefire-reports\\*.xml'

            // Archive artifacts for traceability
            archiveArtifacts artifacts: 'target\\surefire-reports\\*.xml', allowEmptyArchive: true
        }
    }
}
