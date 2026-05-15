## Hostel service

it will gives us  all Hostel,mess related services needed in the university management system like
Room allocation and availability
Hostel fee management
Maintenance requests
Mess management and meal plans
Visitor logs
in this hostelservice I have enabled swagger and keyclaok based authentication and authorization

## Run
.\mvnw spring-boot:run to run exam module
kc.bat start-dev to start keycloak
kafka-server-start.bat config\server.properties to start kakfa

## Ports
hostel-service running on :8093
key cloak running on 8080
kafka running on 9092

# purpose
add Hostel,mess,add student to hostel,select mess,add visitor logs,maintanance request

## setup
to access any of the api presnt in the fee managemnet service
one must click on authorize symbol in the swagger and login

After login ,based on his role he can access only certain end points
STUDENT- maintance request,join in to hostel,add visitor info,selct meal plan
ADMIN - all available rooms in hostel,add hostel,mess,mela plans,rooms,filled rooms in hostel,solve maintanace issue,list of pending maintanace issue

## website
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=auth-service
Login and click on drop down and select  hostelservice or
http://localhost:8081/webjars/swagger-ui/index.html?urls.primaryName=hostel-service