## Exam service

it will gives us  all exam regsiration related services needed in the university management system like
Exam scheduling and timetable --schedular
Hall allocation and seating arrangement
Exam registration
Result processing and publication --schedular
Re-evaluation requests

in this exam service I have enabled swagger and keyclaok based authentication and authorization

## Run
.\mvnw spring-boot:run to run exam module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
exam-service running on :8096
key cloak running on 8080
kafka running on 9092

## depends on
  feemanagement -servcie- for fee paid details

# purpose
appply for exam,revaluation,hall,seat allocation,exam timetable
## setup
to access any of the api presnt in the exam service
one must click on authorize symbol in the swagger and login

After login ,based on his role he can access only certain end points
STUDENT-regsietr for exam,apply for revelauation.get exam schedule
ADMIN - add  exam,add revaluation for exam,add exam hall,add seats to exam hall,automatically allocate exam hall and seat no to all students
## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service
Login and click on drop down and select  examservice or
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=exam-service