## Api Gateway service

it will gives us the routing to each microservice presnt in the university management system 

in this Api gateway service I have enabled swagger and keyclaok based authentication and authorization
## Downloads needed
Java 21
Intellij
kafka_2.13-4.1.2
keycloak

## Run
.\mvnw spring-boot:run to run Api gateway
kc.bat start-dev to start keycloak 
kafka-server-start.bat config\server.properties to start kakfa

## Ports 
Api gateway running on :8081
key cloak running on 8080 
kafka running on 9092

## setup
open localhost:8080 and login with Keycloak Administration details,
create a realm universitymanagement realm ,create a client named university_client 
create roles ADMIN,FACULTY,STUDENT 
Now the url for keycloak Authentication and authorization will be http://localhost:8080/realms/universitymanagementrealm



## website 
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service