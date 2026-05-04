package com.example.facultyservice.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "faculty service API",
                version = "1.0",
                description = "Handles faculty process and all faculty related services like Office hours and availability" +
                        "Performance reviews and ratings" +
                        "Salary and payroll integration"
        ),
        servers = {
                @Server(url = "http://localhost:8081")
        },
        security = @SecurityRequirement(name = "keycloak")
)
public class SwaggerConfig {

}