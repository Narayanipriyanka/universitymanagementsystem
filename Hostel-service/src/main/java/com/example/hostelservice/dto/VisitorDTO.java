package com.example.hostelservice.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class VisitorDTO {
    private String visitorName;
    private UUID studentID;
    private String relation;
    private String reason;
    private LocalTime entryTime;
    private LocalTime exitTime;

    public UUID getStudentID() {
        return studentID;
    }

    public String getRelation() {
        return relation;
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setStudentID(UUID studentID) {
        this.studentID = studentID;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }
}
