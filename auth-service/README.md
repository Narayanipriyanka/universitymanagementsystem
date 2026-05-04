## Auth service

it will gives us the page for first time registration of faculty and student and Login of admin,faculties,students presnt in the university management system

in this Auth service I have enabled swagger and keyclaok based authentication and authorization


## Run
.\mvnw spring-boot:run to run Auth service
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
Auth service running on :8082
key cloak running on 8080
kafka running on 9092

## setup
create a user in the /register api by entering username,password,role 
login into university mnaagemnet system by clicking on authorize button in swaggger enter username,password that are given during registration

## purpose
registration,login of faculty,students,admin

## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service