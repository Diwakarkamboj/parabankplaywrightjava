pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\Program Files\\apache-maven-3.9.10'
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-24'
        PATH = "${JAVA_HOME}\\bin;${MAVEN_HOME}\\bin;${env.PATH}"
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'chromium'], description: 'Select browser for test execution')
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                bat "mvn clean test -Dbrowser=${params.BROWSER}"
            }
        }

        stage('Verify Report Exists') {
            steps {
                bat 'dir /s target\\test-output\\emailable-report.html'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target\\test-output\\emailable-report.html', allowEmptyArchive: true

            publishHTML([
                reportDir: 'target\\test-output',
                reportFiles: 'emailable-report.html',
                reportName: 'TestNG Emailable Report',
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true
            ])
        }
    }
}
