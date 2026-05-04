package com.example.studentservice.entity;

import java.util.UUID;

public class ScholarshipRequest {
    private UUID studentId;
    private Double amount;
    private String scholarshipName;

    public UUID getStudentId() {
        return studentId;
    }

    public ScholarshipStatus getStatus() {
        return status;
    }

    public void setStatus(ScholarshipStatus status) {
        this.status = status;
    }

    public String getScholarshipName() {
        return scholarshipName;
    }

    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
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

    private ScholarshipStatus status;
}
