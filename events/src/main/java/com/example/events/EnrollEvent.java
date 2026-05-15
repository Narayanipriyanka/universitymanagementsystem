package com.example.events;

import java.util.UUID;

public class EnrollEvent {
    private UUID studentId;
    private Long courseId;
    private String program;
    private String courseCode;
    private Integer semester;
    private String deptCode;
    public EnrollEvent(){

    }
    public EnrollEvent(UUID id, String deptCode, Long cId, String program, Integer semester, String courseCode) {
        this.courseCode=courseCode;
        this.courseId=cId;
        this.program=program;
        this.studentId=id;
        this.semester=semester;
        this.deptCode=deptCode;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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
