package com.example.events;

import java.util.List;
import java.util.UUID;

public class CourseEnrollEvent {
    private UUID studentId;
    private List<UUID> courseIds;
    private String semester;

    public UUID getStudentId() {
        return studentId;
    }

    public List<UUID> getCourseIds() {
        return courseIds;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setCourseIds(List<UUID> courseIds) {
        this.courseIds = courseIds;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public CourseEnrollEvent(UUID studentId,List<UUID> courseId, String semester) {
        this.studentId = studentId;
        this.courseIds = courseId;
        this.semester = semester;
    }

    public CourseEnrollEvent() {}

}
