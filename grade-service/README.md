## Grade service

it will gives us  all marrks,cgpa,grade related services needed in the university management system like
Assignment and exam grade entry
Grade calculation
CGPA/SGPA computation
Grade distribution analytics
Transcript generation

in this grade service I have enabled swagger and keyclaok based authentication and authorization

## Run
.\mvnw spring-boot:run to run exam module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
grade-service running on :8094
key cloak running on 8080
kafka running on 9092

# purpose
add marks for a subject scored by student,post results csv file,Marks report,student's academics history

## setup
to access any of the api presnt in the fee managemnet service
one must click on authorize symbol in the swagger and login

After login ,based on his role he can access only certain end points
STUDENT- Academics and Marks report,academics history,result of a student in a semester
ADMIN - pass,failure percentage of semester,post results csv file
FACULTY-add marks for a subject scored by student
## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service
Login and click on drop down and select  grade service or
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=grade-service