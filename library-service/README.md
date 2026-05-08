## Library service

it will gives us the library and digital resources code needed in the university management system
in this library service I have enabled swagger and keyclaok based authentication and authorization

## Run
.\mvnw spring-boot:run to run library module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
library service running on :8089
key cloak running on 8080
kafka running on 9092

## setup
this service has role based access
ADMIN-add book,add pdfs
STUDENT-borrow book,return book,reserve book

## database
create a database -create database librarydatabase;
use librarydatabase;

## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service

click on drop down and select library service or
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=library-service