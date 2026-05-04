## Student service

it will gives us all the student related services needed in the university management system like
Student registration and profiles
Academic history and transcripts
Enrollment in courses per semester
Student ID card generation
Parent/guardian information
Scholarship and financial aid tracking


in this student service I have enabled swagger and keyclaok based authentication and authorization

## Run
.\mvnw spring-boot:run to run student module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
student-service running on :8083
key cloak running on 8080
kafka running on 9092

## setup
to access any of the api presnt in the student service
one must click on authorize symbol in the swagger and login 

After login ,based on his role he can access only certain end points 
STUDENT-apply for id card,scholarship,get scholarship status,get transcripts etc..
ADMIN - generate id card,approve schoalrship,add student file etc..
## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service
click on drop down and select  student service or
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=student-service
