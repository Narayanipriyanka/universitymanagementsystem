package com.example.timtableservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "timetable service API",
                version = "1.0",
                description = "Handles timetable process and all class scheduling related services"
        ),
        servers = {
                @Server(url = "http://localhost:8081")
        },
        security = @SecurityRequirement(name = "keycloak")
)
public class SwaggerConfig {

}