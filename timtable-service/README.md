## timetable service

it will gives us  all clas room and course timetable related services needed in the university management system like
Class scheduling with conflict detection
Room and resource allocation
Faculty availability checking
Student timetable view
Automated timetable generation

in this timetableservice I have enabled swagger and keyclaok based authentication and authorization

## Run
.\mvnw spring-boot:run to run timetable module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa
# depends on
 faculty service-for officehours of faculty
## Ports
timetable-service running on :8097
key cloak running on 8080
kafka running on 9092

# purpose
add classroom,automatically generate timetable based on available rooms and courses,faculty,get timetable

## setup
to access any of the api presnt in the fee managemnet service
one must click on authorize symbol in the swagger and login

After login ,based on his role he can access only certain end points
STUDENT- timetable absed on his class room number
ADMIN - add classroom,add resources,get faculty availablity,automatically generate timetable based on available rooms and courses,faculty
FACULTY-his timetable for each class,
## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service
Login and click on drop down and select  timetableservice or
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=timetable-service