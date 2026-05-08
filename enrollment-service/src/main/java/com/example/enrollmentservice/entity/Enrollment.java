package com.example.enrollmentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<UUID> studentids;
    private String courseCode;
    private Integer sem;
    private LocalDate deadline;

    public Long getId() {
        return id;
    }

    public Integer getSem() {
        return sem;
    }

    public void setSem(Integer sem) {
        this.sem = sem;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public List<UUID> getStudentids() {
        return studentids;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setStudentids(List<UUID> studentids) {
        this.studentids = studentids;
    }

    public List<UUID> getStudentid() {
        return studentids;
    }

    public void setStudentid(List<UUID> studentid) {
        this.studentids = studentid;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
