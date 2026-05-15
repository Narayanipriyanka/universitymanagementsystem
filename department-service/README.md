## Department service

it will gives us all the department,program related services needed in the university management system like
Department information and heads
Program offerings (B.Tech, M.Tech, MBA, etc.)
Budget and resource management

in this department service I have enabled swagger and keyclaok based authentication and authorization

## Run
.\mvnw spring-boot:run to run department module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
department-service running on :8086
key cloak running on 8080
kafka running on 9092

# purpose
add department,program,get department details,add budget to departemnt,allcoate resources based on budget
## setup
to access any of the api presnt in the department service
one must click on authorize symbol in the swagger and login

After login ,based on his role he can access only certain end points
FACULTY-gets information about the departmnets etc..
ADMIN - adds deaprtments,add programs,add budget to each program,resource allocation..
## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service
click on drop down and select  student service or
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=department-service