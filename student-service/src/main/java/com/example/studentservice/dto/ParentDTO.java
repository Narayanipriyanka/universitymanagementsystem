package com.example.studentservice.dto;

import java.util.UUID;

public class ParentDTO {
    private String parentName;
    private String relation;
    private String phone;
    private UUID studentId;

    public String getParentName() {
        return parentName;
    }

    public String getRelation() {
        return relation;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
