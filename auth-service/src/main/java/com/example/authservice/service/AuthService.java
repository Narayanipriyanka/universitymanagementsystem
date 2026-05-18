package com.example.authservice.service;


import com.example.events.RegisterRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    private Keycloak keyCloak;
    @Autowired
    private RegisterProducer producer;

    public String register(RegisterRequest req) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(req.getUsername());
        user.setEnabled(true);
        Response res = keyCloak.realm("universitymanagementrealm")
                .users()
                .create(user);
        if (res.getStatus() != 201) {
            throw new RuntimeException("User creation failed: " + res.getStatus());
        }
        String userId = res.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setType(CredentialRepresentation.PASSWORD);
        cred.setValue(req.getPassword());
        cred.setTemporary(false);
        keyCloak.realm("universitymanagementrealm")
                .users()
                .get(userId)
                .resetPassword(cred);
        RoleRepresentation role=keyCloak.realm("universitymanagementrealm")
                        .roles()
                                .get(req.getRole().toUpperCase())
                                        .toRepresentation();
        keyCloak.realm("universitymanagementrealm")
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(List.of(role));
        producer.send(req);
        return "user registered successfully";
    }

    }
