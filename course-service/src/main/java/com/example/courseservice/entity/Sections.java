package com.example.courseservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;
    private String room;
    private String courseCode;
    private Integer capacity;

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }



    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}