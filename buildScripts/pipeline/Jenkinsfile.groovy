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
            }
        }
        stage('Install PIP packages') {
            steps {
                sh "python3 -m venv env"
                sh '''  . ./env/bin/activate
                        pip install -r requirements.txt
                    '''
            }
        }
        stage ('Test'){
            steps {
                sh ''' . ./env/bin/activate
                        python unit-test.py
                    '''
            }
        }
    }
}
