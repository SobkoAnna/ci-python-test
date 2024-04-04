pipeline {
    agent any
    stages {
        stage ('GIT Checkout'){
            steps {
                git changelog: false, poll: false, url: 'git@github.com:SobkoAnna/ci-python-test.git'
            }
        }
        stage('build') {
            steps {
                sh 'pip install -r requirements.txt'
            }
        }
        stage ('Test'){
            steps {
                sh 'python unit-test.py'
            }
        }
    }
}
