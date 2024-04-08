pipeline {
    agent any
    environment {
        accessToken = credentials('kgithub')
    }
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
                        pytest tests/unit-test.py  --no-header -v
                    '''
            }
        }
        stage('Merge with Main') {
            steps {
                script {
                    sshagent(credentials: ['jenkins_ci_user']) {
                        sh("git config --global user.email 'annsobko2022@gmail.com'")
                        sh("git config --global user.name 'SobkoAnna' --replace-all")
                        sh "git checkout main"
                        
                        sh "git pull origin main"
                        def mergeOutput = sh(script: "git merge --no-ff origin/${ghprbSourceBranch}", returnStatus: true)
                        
                        if (mergeOutput == 0) {
                            echo "Pull Request successfully merged!"
                            sh "git push origin main"
                            def prNumber = "${ghprbPullId}"
                            def apiUrl = "https://api.github.com/repos/owner/repository/pulls/${prNumber}"
                            def requestBody = '{"state": "closed"}'
                            def response = sh(script: "curl -X PATCH -H 'Authorization: token ${accessToken}' -d '${requestBody}' '${apiUrl}'", returnStdout: true)
                            echo "Response from GitHub API: ${response}"
                        } else {
                            error "Failed to merge Pull Request!"
                        }
                    }
                }
            }
        }
    }
}
