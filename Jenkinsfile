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
            archive 'build/libs/**/*.jar'
            junit 'build/reports/tests/**/*.html'
        }
    }
}