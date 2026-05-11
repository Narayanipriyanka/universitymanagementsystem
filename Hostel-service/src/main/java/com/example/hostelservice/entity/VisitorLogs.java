package com.example.hostelservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
public class VisitorLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String visitorName;
    private UUID studentID;
    private String relation;
    private LocalDate visitDate;
    private String reason;
    private LocalTime entryTime;
    private LocalTime exitTime;

    public String getVisitorName() {
        return visitorName;
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

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public UUID getStudentID() {
        return studentID;
    }

    public void setStudentID(UUID studentID) {
        this.studentID = studentID;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
