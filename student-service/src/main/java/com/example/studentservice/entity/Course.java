package com.example.studentservice.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID studentId;
    private UUID courseId;
    private String program;
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String semester;

    public void setCourseID(UUID courseId) {
        this.courseId=courseId;
    }
}
