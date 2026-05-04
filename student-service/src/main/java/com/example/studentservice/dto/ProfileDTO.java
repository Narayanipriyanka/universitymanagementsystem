package com.example.studentservice.dto;

import com.example.studentservice.entity.StudentStatus;

import java.util.UUID;

public class ProfileDTO {
    private UUID studentId;
    private String name;
    private Integer currentSemester;
    private String course;
    private Integer currentYear;
    private String department;
    private StudentStatus status;

    public String getName() {
        return name;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public Integer getCurrentSemester() {
        return currentSemester;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    public void setCurrentSemester(Integer currentSemester) {
        this.currentSemester = currentSemester;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setName(String name) {
        this.name = name;
    }
}
