package com.example.events;

import java.util.UUID;

public class EnrollEvent {
    private UUID studentId;
    private String program;
    private String courseCode;
    private Integer semester;
    public EnrollEvent(UUID id, String program, Integer semester, String courseCode) {
        this.courseCode=courseCode;
        this.program=program;
        this.studentId=id;
        this.semester=semester;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
