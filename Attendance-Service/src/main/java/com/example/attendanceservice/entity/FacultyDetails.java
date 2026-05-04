package com.example.attendanceservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class FacultyDetails {
    @Id
    private UUID facultyId;
    private String email;
    private String username;
    private String password;

    public UUID getFacultyId() {
        return facultyId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFacultyId(UUID facultyId) {
        this.facultyId = facultyId;
    }
}
