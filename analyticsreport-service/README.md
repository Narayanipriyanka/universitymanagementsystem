## Analytics and report service

it will gives us the fee ,course completion analytics and departwise wise report related code needed in the university management system
in this analytics and report service I have enabled swagger and keyclaok based authentication and authorization
this service inlcudes Student performance reports
Department-wise analytics
Attendance dashboards
Fee collection reports
Course completion rates

## Run
.\mvnw spring-boot:run to run analytics report module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
analytics and report service running on :8099
key cloak running on 8080
kafka running on 9092

## depends on
student-service- for student details,student enroll course details
grade-service-for academic details
attendance-service -for faculty attendance details
feemanagemnet-service-for payment details

## setup
this service has services accessed based on role
ADMIN-get attendance of faculties analytics in a month,get attendance based on percnetage,get attendance of a faculty in amonth,
get course completion rate,get fee collection analytics rate in amonth,get fee collection report in amonth,get students performance in a course,get department wise students performance

## database
create a database : create database analyticsdatabase;
use notificationdatabase;

## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service

Login and click on drop down and select analytics service
or
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=analytics-service
