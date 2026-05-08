package com.example.notificationcommunicationservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class StudentDetails {
    @Id
    private UUID Id;
    private String username;
    private String email;

    public UUID getId() {
        return Id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(UUID id) {
        Id = id;
    }
}
