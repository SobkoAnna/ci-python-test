FROM jenkins/jenkins:latest
USER root
RUN apt-get update && apt-get install -y curl
RUN apt-get install -y python3 python3-pip
USER jenkins