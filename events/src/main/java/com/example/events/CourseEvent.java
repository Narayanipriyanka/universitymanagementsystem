package com.example.events;

import java.util.UUID;

public class CourseEvent {
    private String courseName;
    private Integer semester;
    private String program;
    private String code;
    private String deptCode;
    private Long credits;
    public CourseEvent() {
    }
    public CourseEvent(String code, String departmentCode, Long credits, String name, Integer sem, String programCode) {
    this.courseName=name;
    this.credits=credits;
    this.program=programCode;
    this.semester=sem;
    this.deptCode=departmentCode;
    this.code=code;
    }

    public Integer getSemester() {
        return semester;
    }

    public String getCode() {
        return code;
    }

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
