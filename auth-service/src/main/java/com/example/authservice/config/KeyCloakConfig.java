package com.example.authservice.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyCloakConfig {
    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080")
                .realm("master")
                .clientId("admin_client")
                .clientSecret("x2Vok6Q42LeBybOtoMRCQgrITtj0vHTN")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }
}
