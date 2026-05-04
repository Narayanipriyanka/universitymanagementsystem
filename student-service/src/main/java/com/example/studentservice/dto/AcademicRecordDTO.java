package com.example.studentservice.dto;

import java.util.UUID;

public class AcademicRecordDTO {
    private UUID studentId;
    private String semester;
    private String subject;
    private Double marks;

    public UUID getStudentId() {
        return studentId;
    }

    public String getSemester() {
        return semester;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
