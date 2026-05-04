package com.example.facultyservice.entity;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String courseName;
    private Integer semester;
    private String program;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    @Enumerated
    private CourseAllocationStatus status;

    public Integer getSemester() {
        return semester;
    }

    public CourseAllocationStatus getStatus() {
        return status;
    }

    public void setStatus(CourseAllocationStatus status) {
        this.status = status;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Long getId() {
        return id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
