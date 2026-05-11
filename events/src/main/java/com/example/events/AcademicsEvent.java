package com.example.events;

import java.util.UUID;

public class AcademicsEvent {
    private UUID studentId;
    private Integer semester;
    private String subject;
    private String grade;
    private Double marks;
    private Boolean isPass;
    public AcademicsEvent(UUID studentId, String subject, Double marks, Integer semester, String grade,Boolean isPass) {
    this.grade=grade;
    this.studentId=studentId;
    this.marks=marks;
    this.semester=semester;
    this.subject=subject;
    this.isPass=isPass;}

    public UUID getStudentId() {
        return studentId;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Boolean getPass() {
        return isPass;
    }

    public void setPass(Boolean pass) {
        isPass = pass;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
