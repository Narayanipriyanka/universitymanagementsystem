package com.example.studentservice.dto;

import com.example.studentservice.entity.ScholarshipStatus;

import java.util.UUID;

public class ScholarshipStatusDTO {
    private UUID studentId;
    private Double amount;
 private ScholarshipStatus status;
    public UUID getStudentId() {
        return studentId;
    }

    public ScholarshipStatus getStatus() {
        return status;
    }

    public void setStatus(ScholarshipStatus status) {
        this.status = status;
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
