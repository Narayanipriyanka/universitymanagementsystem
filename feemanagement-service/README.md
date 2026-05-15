## Fee Management service

it will gives us  all fee related services needed in the university management system like
Fee structure by program/semester
Fee payment tracking
Fee receipts and invoices
Overdue notifications

in this feemanagemnet service I have enabled swagger and keyclaok based authentication and authorization

## Run
.\mvnw spring-boot:run to run exam module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
feemanagemnet-service running on :8095
key cloak running on 8080
kafka running on 9092

# depends on
  library service -for library fee dues
  hostel-service- for hostel fee 
  exam-service -for exam fee
  student-service- for tution fee

# purpose
add fee structure,pay fees,generate fee recipets or invoices,
## setup
to access any of the api presnt in the fee managemnet service
one must click on authorize symbol in the swagger and login

After login ,based on his role he can access only certain end points
STUDENT-pay fees,get current status of fee payment,get fees of different semesters in a program
ADMIN - add fees to a semester,
## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service
Login and click on drop down and select  feemanagement service or
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=feemanagemnet-service