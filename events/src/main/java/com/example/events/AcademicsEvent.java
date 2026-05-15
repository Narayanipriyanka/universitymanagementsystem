package com.example.events;

import java.util.UUID;

public class AcademicsEvent {
    private UUID studentId;
    private Integer semester;
    private String subject;
    private String programCode;
    private String grade;
    private Double marks;
    private Boolean isPass;
    public AcademicsEvent(){

    }
    public AcademicsEvent(UUID studentId, String subject, String programCode, Double marks, Integer semester, String grade, Boolean isPass) {
    this.grade=grade;
    this.studentId=studentId;
    this.marks=marks;
    this.programCode=programCode;
    this.semester=semester;
    this.subject=subject;
    this.isPass=isPass;}

    public UUID getStudentId() {
        return studentId;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
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

    public Integer getSemester() {
        return semester;
    }



    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
