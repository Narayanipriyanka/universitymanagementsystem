package com.example.studentservice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseCode;
    private String program;
    private List<String> materialPaths;
    private String syllabusPath;
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public List<String> getMaterialPaths() {
        return materialPaths;
    }

    public String getSyllabusPath() {
        return syllabusPath;
    }

    public void setSyllabusPath(String syllabusPath) {
        this.syllabusPath = syllabusPath;
    }

    public void setMaterialPaths(List<String> materialPaths) {
        this.materialPaths = materialPaths;
    }

    public String getCourseCode() {
        return courseCode;
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

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }


    public void setId(Long id) {
        this.id = id;
    }

    private Integer semester;



}
