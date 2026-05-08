package com.example.hostelservice.dto;

import java.util.UUID;

public class MaintananceRequestDTO {
    private UUID studentId;
    private String issueIn;
    private String description;

    public UUID getStudentId() {
        return studentId;
    }

    public String getIssueIn() {
        return issueIn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIssueIn(String issueIn) {
        this.issueIn = issueIn;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
