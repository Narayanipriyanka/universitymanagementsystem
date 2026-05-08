package com.example.hostelservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class MaintananceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID studentId;
    private String issueIn;
    private String description;
    private LocalDate issueDate;
    private MaintananceStatus status;

    public Long getId() {
        return id;
    }

    public MaintananceStatus getStatus() {
        return status;
    }

    public void setStatus(MaintananceStatus status) {
        this.status = status;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIssueIn() {
        return issueIn;
    }

    public void setIssueIn(String issueIn) {
        this.issueIn = issueIn;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
