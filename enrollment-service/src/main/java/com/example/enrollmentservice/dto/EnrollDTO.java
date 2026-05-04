package com.example.enrollmentservice.dto;

import java.util.UUID;

public class EnrollDTO {
    private UUID studentId;
    private String courseCode;
    private Integer sem;

    public UUID getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Integer getSem() {
        return sem;
    }

    public void setSem(Integer sem) {
        this.sem = sem;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
