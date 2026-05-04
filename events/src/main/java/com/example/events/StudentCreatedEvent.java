package com.example.events;

public class StudentCreatedEvent {
    private String email;
    private String username;
    private String password;
    private String studentStatus;

    public StudentCreatedEvent(String email, String username, String password, String studentStatus) {
        this.email=email;
        this.username=username;
        this.password=password;
        this.studentStatus=studentStatus;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
