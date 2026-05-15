package com.example.events;

import java.util.List;
import java.util.UUID;

public class CourseEnrollEvent {
    private String courseCode;
    private List<UUID> studentIds;
    private Integer semester;
public CourseEnrollEvent(){

}
    public CourseEnrollEvent(String courseCode, Integer sem, List<UUID> studentid) {
    this.courseCode=courseCode;
    this.semester=sem;
    this.studentIds=studentid;}

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public List<UUID> getStudentIds() {
        return studentIds;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setStudentIds(List<UUID> studentIds) {
        this.studentIds = studentIds;
    }




}
