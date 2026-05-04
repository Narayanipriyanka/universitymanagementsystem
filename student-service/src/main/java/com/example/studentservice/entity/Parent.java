package com.example.studentservice.entity;

import jakarta.persistence.*;

@Entity
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parentName;
    private String relation;
    private String phone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    public String getParentName() {
        return parentName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getRelation() {
        return relation;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
