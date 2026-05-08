package com.example.enrollmentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;
import java.util.UUID;

@Entity
public class Section {
    @Id
    private Long sectionId;
    private String room;
    private String courseCode;
    private Integer capacity;
    private Integer availableSeats;
    private Integer allocatedSeats;
    private List<UUID> studentIds;
    public Section(Long sectionId, Integer capacity, String room, String courseCode) {
        this.sectionId=sectionId;
        this.room=room;
        this.courseCode=courseCode;
        this.capacity=capacity;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public List<UUID> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<UUID> studentIds) {
        this.studentIds = studentIds;
    }

    public Integer getAllocatedSeats() {
        return allocatedSeats;
    }

    public void setAllocatedSeats(Integer allocatedSeats) {
        this.allocatedSeats = allocatedSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Section() {

    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getRoom() {
        return room;
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

    public void setRoom(String room) {
        this.room = room;
    }

}
