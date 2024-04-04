pipeline {
    agent any
    stages {
        stage('Git Clone') {
            steps {
                sshagent(['jenkins_ci_user']) {
                    sh 'git clone -b ${params.BRANCH_NAME} git@github.com:SobkoAnna/ci-python-test.git'
                }
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
