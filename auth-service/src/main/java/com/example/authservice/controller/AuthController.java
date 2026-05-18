package com.example.authservice.controller;

import com.example.authservice.service.AuthService;
import com.example.events.RegisterRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@Tag(name="University Auth controller",description="for first time registration and login to university services users")
public class AuthController {
    @Autowired
    private AuthService service;
    @Operation(summary = "Register user", description = "for first time register click  here,Register as student or faculty based on role ")
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return service.register(request);
    }
    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        String logoutUrl = "http://localhost:8080/realms/universitymanagementrealm/protocol/openid-connect/logout" + "?redirect_uri=http://localhost:8081/swagger-ui/index.html";
        response.sendRedirect(logoutUrl);
    }
}
