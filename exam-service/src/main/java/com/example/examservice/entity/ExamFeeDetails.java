package com.example.examservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;
@Entity
public class ExamFeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID studentId;
    private String courseCode;
    private String programCode;
    private Integer semester;
    private Boolean feePaid;


    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getProgramCode() {
        return programCode;
    }

    public Boolean getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(Boolean feePaid) {
        this.feePaid = feePaid;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String deptCode) {
        this.courseCode = deptCode;
    }
}
