pipeline {
    agent { docker 'gradle' }
    stages {
        stage('build') {
            steps {
                sh 'gradlew build'
            }
        }
    }
}