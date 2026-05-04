package com.example.facultyservice.dto;

import java.util.UUID;

public class QualificationDto {
    private UUID facultyId;
    private String degree;
    private String specializaton;
    private Long yearOfCompletion;
    private String university;

    public String getDegree() {
        return degree;
    }

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

    public String getUniversity() {
        return university;
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

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public UUID getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(UUID facultyId) {
        this.facultyId = facultyId;
    }

    private String certificatePath;
}
