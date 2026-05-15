package com.example.events;

import java.util.UUID;

public class SyllabusEvent {
    private String email;
    private UUID id;
    private String filePath;
   private String courseCode;
public SyllabusEvent(){

}
    public SyllabusEvent(String syllabusPath, String code) {
        this.filePath=syllabusPath;
        this.courseCode=code;
    }

    public SyllabusEvent(String email, String syllabusPath, String courseCode,UUID id) {
    this.email=email;
    this.filePath=syllabusPath;
    this.id=id;
    this.courseCode=courseCode;}

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
