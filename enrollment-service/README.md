## Enrollment service

it will gives us all the course registration,adding student to course section ,drop a course and all course regsiration related services needed in the university management system like
Course registration with prerequisites validation
Add/drop courses within deadlines
Section selection and seat availability
Registration holds (fees, academic probation)
Course capacity management

in this enrollment service I have enabled swagger and keyclaok based authentication and authorization

## Run
.\mvnw spring-boot:run to run enrollment module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
enrollment-service running on :8092
key cloak running on 8080
kafka running on 9092
## depends on
 student-service -for student enroll details
 
# purpose
add student to a section,get reamining seats of a course,drop from a course
## setup
to access any of the api presnt in the enrollment service
one must click on authorize symbol in the swagger and login

After login ,based on his role he can access only certain end points
STUDENT-drop from course,get available seats in a course
ADMIN - adds student to section if he has cleared all dues and status is active
## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service
click on drop down and select  enrollmentservice or
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=enrollment-service