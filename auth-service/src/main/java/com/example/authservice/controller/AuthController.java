package com.example.authservice.controller;

import com.example.authservice.service.AuthService;
import com.example.events.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

}
