pipeline {
    agent any
    stages {
        stage('Build and Test') {
            steps {
                sh './gradlew build'
            }
        }

        stage('Deploy - Qa') {
            steps {
                echo 'Deploy - Qa'
            }
        }

        stage('Sanity check') {
            steps {
                input "Does the qa environment look ok?"
            }
        }
        stage('Deploy - Prod') {
            steps {
                echo 'Deployinggg'
            }
        }
    }
    post {
        success {
            archive 'build/libs/tests/**/*.jar'
            deleteDir()
        }
        always {
            mail to: 'coskundeniz1989@gmail.com',
                 subject: "Pipeline: ${currentBuild.fullDisplayName}",
                 body: "Something is happen with ${env.BUILD_URL}"
            junit 'build/reports/tests/test/**/*.html'
        }
    }
}