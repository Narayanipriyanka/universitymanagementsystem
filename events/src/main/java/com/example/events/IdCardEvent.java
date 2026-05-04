package com.example.events;

public class IdCardEvent {
    private String email;
    private String filePath;

    public IdCardEvent(String email, String filePath) {
    this.email=email;
    this.filePath=filePath;}

    public String getEmail() {
        return email;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
