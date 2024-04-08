### 1. Create container by Dockerfile
**For it need go to folder *docker* and run commands:**
```bash
- podman build -t epam-jenkins .
- podman run -d --name jenkins-container-master -p 8080:8080 epam-jenkins
```
### 2. Create a private key that will be added to jenkins and github to create a connection between them

### 3. Create the repository on github and loaded unit-test.py file. 

### 4. Create jenkins pipeline job and configure it take jenkinsfile from github repository
![image](https://github.com/SobkoAnna/ci-python-test/assets/165591482/a2cf2bd3-02e8-4a61-8e06-0dd9e212bd9c)


### 5. Created and configured webhook for running job for creating/changing PR
![image](https://github.com/SobkoAnna/ci-python-test/assets/165591482/d1b7c3d2-ddec-4002-89f2-951074b5c16f)


### 6. After successfully running the tests, the PR will be merged and closed
![image](https://github.com/SobkoAnna/ci-python-test/assets/165591482/f143b026-9efa-4d21-9b5c-ce10cc4a8a6e)


![image](https://github.com/SobkoAnna/ci-python-test/assets/165591482/2c54ba41-46dc-40a3-a1a4-3f53980d42d3)


