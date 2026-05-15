## course service

it will gives us the course related code like add course,get al courses etc..needed in the university management system
in this analytics and report service I have enabled swagger and keyclaok based authentication and authorization
this service inlcudes 
Course catalog (code, name, credits, prerequisites)
Course sections with timings and room assignments
Semester-wise course offerings
Course materials and syllabus upload

## Run
.\mvnw spring-boot:run to run course service module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
course service running on :8098
key cloak running on 8080
kafka running on 9092

## setup
this service has services accessed based on role
ADMIN-add a course,add a section to a course,upload course materials,uplaod course syllabus
STUDENT-get all courses in asem,get sections in a course
FACULTY-get course details,get all courses in asem,get sections in a course
## database
create a database : create database coursedatabase;
use coursedatabase;

## purpose
add course,syllabus,materials,sections

## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service

Login and click on drop down and select course service
or
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=course-service
