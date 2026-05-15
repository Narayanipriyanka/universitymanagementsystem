## Notification communication service

it will gives us the notification and email sending related code needed in the university management system
in this notification communication service I have enabled swagger and keyclaok based authentication and authorization

## Run
.\mvnw spring-boot:run to run Notification communication module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
Api gateway running on :8089
key cloak running on 8080
kafka running on 9092

## depends on
 students service-student,parent details
 faculty-servcie-faculty details
 course-service- for course syllabus,materials details
 attendansce-service -for faculty attednance
 feemanagement-servcie -for paymnet info
 library-service -for books,resources
 grade-service -for academics
## setup
this service has some automatic email sending mechanism where it consumes events like ID card generated event,leave event ,etc.. based on the event 
consumed it is redirected to emailsender and sends the appropriate mail related to the event consumed automatically to the respective user

## database
create a database -create database notificationdatabase;
use notificationdatabase;

## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service

click on drop down and select notification communication service