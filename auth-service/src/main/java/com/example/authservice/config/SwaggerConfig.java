package com.example.authservice.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.*;

import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Auth service API",
                version = "1.0",
                description = "Handles registration process and integrates with Keycloak for login so click on authorize symbol to login "
        ),
        security = @SecurityRequirement(name = "keycloak")
)
@SecurityScheme(
        name = "keycloak",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        authorizationUrl = "http://localhost:8080/realms/universitymanagementrealm/protocol/openid-connect/auth",
                        tokenUrl = "http://localhost:8080/realms/universitymanagementrealm/protocol/openid-connect/token",
                        scopes = {
                                @OAuthScope(name = "openid", description = "OpenID"),
                                @OAuthScope(name = "profile", description = "Profile")
                        }
                )
        )
)
public class SwaggerConfig {

}
