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
        always {
            junit 'build/reports/tests/**/*.html'
            mail to: 'coskundeniz1989@gmail.com',
                 subject: "Pipeline: ${currentBuild.fullDisplayName}",
                 body: "Something is happen with ${env.BUILD_URL}"
        }
        success {
            archive 'build/libs/**/*.jar'
            deleteDir()
        }
    }
}