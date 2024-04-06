FROM jenkins/jenkins:latest
USER root
RUN apt-get update && apt-get install -y curl && apt-get install -y software-properties-common && apt-get clean
RUN apt-get update
RUN apt-get install -y python3.11 python3.11-venv && apt-get clean
USER jenkins
EXPOSE 8080