package com.example.enrollmentservice.dto;

import java.time.LocalDate;
import java.util.UUID;

public class EnrollDTO {
    private UUID studentId;
    private String courseCode;
    private Integer sem;
    private LocalDate deadLine;
    public UUID getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Integer getSem() {
        return sem;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
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
