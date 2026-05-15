package com.example.events;

import java.util.UUID;

public class FacultyCreatedEvent {
    private UUID facultyId;
    private String email;
    private String username;
    private String password;
public FacultyCreatedEvent(){

}
    public FacultyCreatedEvent(UUID facultyId,String email, String username, String password) {
        this.facultyId=facultyId;this.email=email;
        this.username=username;
        this.password=password;
    }

    public UUID getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(UUID facultyId) {
        this.facultyId = facultyId;
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
}
