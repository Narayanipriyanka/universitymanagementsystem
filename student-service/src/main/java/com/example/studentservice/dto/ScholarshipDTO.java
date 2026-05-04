package com.example.studentservice.dto;

import java.util.UUID;

public class ScholarshipDTO {
    private UUID studentId;
    private Double amount;

    public UUID getStudentId() {
        return studentId;
    }

    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
    }

    public String getScholarshipName() {
        return scholarshipName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    private String scholarshipName;
}
