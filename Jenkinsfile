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
            junit 'build/reports/tests/**/*.html'
        }
    }
}