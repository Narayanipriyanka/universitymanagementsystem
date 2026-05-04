package com.example.studentservice.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Scholarship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID studentID;
    @Enumerated(EnumType.STRING)
    private ScholarshipStatus status;
    private String scholarshipName;
    private Double amount;
@ManyToOne
@JoinColumn(name="student_id")
private Student student;
    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getScholarshipName() {
        return scholarshipName;
    }
    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
    }

    public ScholarshipStatus getStatus() {
        return status;
    }

    public void setStatus(ScholarshipStatus status) {
        this.status = status;
    }

    public UUID getStudentID() {
        return studentID;
    }

    public void setStudentID(UUID studentID) {
        this.studentID = studentID;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
