package com.example.events;

import java.util.UUID;

public class ParentEvent {
    private String name;
    private UUID studentId;
    private String email;
    private String relation;
    private String phone;
public ParentEvent(){

}
    public ParentEvent(String email, UUID id, String parentName, String phone, String relation) {
    this.email=email;
    this.name=parentName;
    this.studentId=id;
    this.relation=relation;
    this.phone=phone;}

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
