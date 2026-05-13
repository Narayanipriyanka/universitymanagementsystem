package com.example.events;

import java.util.UUID;

public class FacultyCourseEvent {
    private UUID facultyId;
    private String facultyName;
    private Long courseId;
   private Integer semester;
   private String programCode;
   private String deptCode;
   private String courseCode;
    public FacultyCourseEvent(UUID facultyId, String firstname, Long courseId, String program, Integer semester, String deptCode, String courseCode) {
        this.courseId=courseId;
        this.facultyId=facultyId;
        this.programCode=program;
        this.semester=semester;
        this.deptCode=deptCode;
        this.courseCode= courseCode;
        this.facultyName=firstname;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Integer getSemester() {
        return semester;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public UUID getFacultyId() {
        return facultyId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setFacultyId(UUID facultyId) {
        this.facultyId = facultyId;
    }
}
