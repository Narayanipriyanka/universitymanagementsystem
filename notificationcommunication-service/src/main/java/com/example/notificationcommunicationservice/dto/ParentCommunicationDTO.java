package com.example.notificationcommunicationservice.dto;

import java.util.UUID;

public class ParentCommunicationDTO {
    private String parentName;
    private UUID studentId;
    private String issue;
    private String description;

    public String getParentName() {
        return parentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }



    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
