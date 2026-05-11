package com.example.gradeservice.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.*;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("keycloak",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .flows(new OAuthFlows()
                                                .authorizationCode(
                                                        new OAuthFlow()
                                                                .authorizationUrl("http://localhost:8080/realms/universitymanagementrealm/protocol/openid-connect/auth")
                                                                .tokenUrl("http://localhost:8080/realms/universitymanagementrealm/protocol/openid-connect/token")
                                                )
                                        )
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("keycloak"));
    }
    @Bean
    public GroupedOpenApi studentApi() {
        return GroupedOpenApi.builder()
                .group("grade")
                .pathsToMatch("/grade/**")
                .build();
    }
}
