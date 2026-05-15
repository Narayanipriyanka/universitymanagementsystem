## Faculty service

it will gives us all the Faculty related services needed in the university management system like
Faculty profiles and qualifications
Department assignments 
Course assignments per semester 
Office hours and availability
Performance reviews and ratings
Salary and payroll integration

in this Faculty service I have enabled swagger and keyclaok based authentication and authorization

## Run
.\mvnw spring-boot:run to run faculty service module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
student-service running on :8084
key cloak running on 8080
kafka running on 9092
## depedns on
 auth servcie-for faculty regsiteration info
## database
create a database -create database facultydatabase; in mysql workbench
use facultydatabase;

## setup
to access any of the api present in the faculty service
one must click on authorize symbol in the swagger and login

After login ,based on his role he can access only certain end points
FACULTY-add his own qulaifications,get his performance reviews etc..
ADMIN - add office hours,add payslip,get performance analysis of faculties,add faculty file etc..
STUDENT- give performance reviews to each of the faculty 
## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service
click on drop down and select  faculty service or
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=faculty-service