pipeline {
    agent any
    stages {
        stage('Git Clone') {
            steps {
                script{
                    git(
                        url: 'git@github.com:SobkoAnna/ci-python-test.git',
                        credentialsId: 'jenkins_ci_user',
                        branch: "${branch}"
                    )
                }
                echo "done"
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
