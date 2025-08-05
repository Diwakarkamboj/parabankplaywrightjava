pipeline {
    agent any

    options {
        skipDefaultCheckout(true) // Avoid automatic checkout issues
    }

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
                bat 'taskkill /F /IM java.exe || echo No java process to kill'
            }
        }

        stage('Checkout Code') {
            steps {
                checkout([$class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[url: 'https://github.com/Diwakarkamboj/parabankplaywrightjava']]
                ])
            }
        }

        stage('Sanity Check') {
            steps {
                bat 'dir /b && if not exist pom.xml exit 1'
            }
        }

        stage('Run Tests') {
    steps {
        script {
            def testStatus = bat(script: "mvn clean test -Dbrowser=${params.BROWSER}", returnStatus: true)
            if (testStatus != 0) {
                error("❌ Tests failed. Check Maven output above.")
            }
        }
        echo "🧪 Tests executed with browser: ${params.BROWSER}"
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
