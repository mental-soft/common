pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh './gradlew build'
            }
        }
    }
    post {
        always {
            deleteDir()
            junit 'build/reports/tests/**/*.html'
            mail to: 'team@example.com',
                 subject: "Pipeline: ${currentBuild.fullDisplayName}",
                 body: "Something is happen with ${env.BUILD_URL}"
        }
        success {
            archive 'build/libs/**/*.jar'
        }
    }
}