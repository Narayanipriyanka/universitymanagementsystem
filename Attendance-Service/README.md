## Attendance service

it will gives us all the Attednance,leave related services needed in the university management system like
Mark daily attendance by faculty
Attendance reports and analytics
Automated alerts for low attendance-schedular
Leave applications and approvals
in this Attednance service I have enabled swagger and keyclaok based authentication and authorization

## Run
.\mvnw spring-boot:run to run Attendance module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
Attedance-service running on :8087
key cloak running on 8080
kafka running on 9092

## setup
to access any of the api presnt in the Attendance service
one must click on authorize symbol in the swagger and login

After login ,based on his role he can access only certain end points
FACULTY-adds attendance everyday,apply for leave etc..
ADMIN - approves leave,gets attednance reports,etc..
## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service
click on drop down and select  student service or
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=Attendance-service