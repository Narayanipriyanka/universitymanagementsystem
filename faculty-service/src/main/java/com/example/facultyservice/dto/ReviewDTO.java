package com.example.facultyservice.dto;

import java.util.UUID;

public class ReviewDTO {
    private UUID facultyId;
    private String facultyName;
    private String department;
    private Integer rating;
    private String review;

    public UUID getFacultyId() {
        return facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public Integer getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public void setFacultyId(UUID facultyId) {
        this.facultyId = facultyId;
    }
}