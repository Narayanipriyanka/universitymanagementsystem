package com.example.courseservice.dto;

public class SectionDTO {
    private String room;
    private String courseCode;
    private Integer capacity;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
