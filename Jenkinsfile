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
    }
    post {
        success {
            archive 'build/libs/**/*.jar'
        }
        always {
            junit 'build/test-results/test/*.xml'
            mail to: 'coskundeniz1989@gmail.com',
                 subject: "Pipeline: ${currentBuild.fullDisplayName}",
                 body: "Something is happen with ${env.BUILD_URL}"
        }
    }
}