# ci-python-test
Jenkins CI pipeline with step by step running python tests

# create container by Dockerfile
podman build -t epam-jenkins .
podman run -d --name jenkins-container-master -p 8080:8080 epam-jenkins

