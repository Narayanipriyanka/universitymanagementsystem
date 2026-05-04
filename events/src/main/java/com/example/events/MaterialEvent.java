package com.example.events;

import java.util.UUID;

public class MaterialEvent {
    private String email;
    private UUID id;
    private String filePath;
    private String courseCode;

    public MaterialEvent(String materialPath, String courseCode,String email,UUID id) {
        this.filePath=materialPath;
        this.courseCode=courseCode;
        this.email=email;
        this.id=id;
    }

    public MaterialEvent(String materialPath, String courseCode) {
        this.filePath=materialPath;
        this.courseCode=courseCode;
    }

    public String getEmail() {
        return email;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
