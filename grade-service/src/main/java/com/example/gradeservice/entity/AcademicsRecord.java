package com.example.gradeservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class AcademicsRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID studentId;
    private Integer semester;
    private String course;
    private Double midMarks;
    private Double FinalExamMarks;
    private Double quizMarks;
    private Double LabMarks;
    private String grade;
    private Double totalMarks;
    private String programCode;
    private Boolean isPass;
    public Integer getSemester() {
        return semester;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public Boolean getPass() {
        return isPass;
    }

    public void setPass(Boolean pass) {
        isPass = pass;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getCourse() {
        return course;
    }

    public Double getMidMarks() {
        return midMarks;
    }

    public Double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Double getLabMarks() {
        return LabMarks;
    }

    public void setLabMarks(Double labMarks) {
        LabMarks = labMarks;
    }

    public Double getQuizMarks() {
        return quizMarks;
    }

    public void setQuizMarks(Double quizMarks) {
        this.quizMarks = quizMarks;
    }

    public Double getFinalExamMarks() {
        return FinalExamMarks;
    }

    public void setFinalExamMarks(Double finalExamMarks) {
        FinalExamMarks = finalExamMarks;
    }

    public void setMidMarks(Double midMarks) {
        this.midMarks = midMarks;
    }

    public Double getMarks() {
        return totalMarks;
    }

    public void setMarks(Double marks) {
        this.totalMarks = marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
