# create container by Dockerfile
For it need go to folder buildScripts and run commands:
**podman build -t epam-jenkins .**
**podman run -d --name jenkins-container-master -p 8080:8080 epam-jenkins**

# Create a private key that will be added to jenkins and github to create a connection between them

# Create the repository on github and loaded unit-test.py file. 

# Create jenkins pipeline job and configure it take jenkinsfile from github repository

# Created and configured webhook for running job for creating/changing PR

# After successfully running the tests, the PR will be merged and closed



