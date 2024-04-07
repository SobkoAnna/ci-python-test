pipeline {
    agent any
    stages {
        stage('Git Clone') {
            steps {
                script{
                    git(
                        url: 'git@github.com:SobkoAnna/ci-python-test.git',
                        credentialsId: 'jenkins_ci_user',
                        branch: "${ghprbSourceBranch}"
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
                        pytest unit-test.py  --no-header -v
                    '''
            }
        }
        stage('Merge with Main') {
            steps {
                script {
                    sshagent(credentials: ['jenkins_ci_user']) {
                        sh("git config --global user.email 'annsobko2022@gmail.com'")
                        sh("git config --global user.name 'SobkoAnna' --replace-all")
                        sh("git pull origin main")
                        sh("git commit -am 'Merged feature branch into main'")
                        sh("git merge main")
                    }
                }
            }
        }
    }
}
