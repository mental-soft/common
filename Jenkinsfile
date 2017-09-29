pipeline {
    agent { docker 'gradle' }
    stages {
        stage('build') {
            steps {
                sh 'gradle -version'
            }
        }
    }
}