package com.example.facultyservice.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String degree;
    private String specializaton;
    private Long yearOfCompletion;
    private String university;
    private String certificatePath;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getUniversity() {
        return university;
    }

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Long getYearOfCompletion() {
        return yearOfCompletion;
    }

    public void setYearOfCompletion(Long yearOfCompletion) {
        this.yearOfCompletion = yearOfCompletion;
    }

    public String getSpecializaton() {
        return specializaton;
    }

    public void setSpecializaton(String specializaton) {
        this.specializaton = specializaton;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
