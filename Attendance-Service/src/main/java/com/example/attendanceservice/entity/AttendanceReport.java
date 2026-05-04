package com.example.attendanceservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;
@Entity
public class AttendanceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID facultyId;
    private String month;
    private Integer totalDays;
    private Integer present;
    private Integer absent;
    private Integer leave;
    private Integer attendancePercentage;

    public UUID getFacultyId() {
        return facultyId;
    }

    public String getMonth() {
        return month;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(Integer attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    public Integer getLeave() {
        return leave;
    }

    public void setLeave(Integer leave) {
        this.leave = leave;
    }

    public Integer getAbsent() {
        return absent;
    }

    public void setAbsent(Integer absent) {
        this.absent = absent;
    }

    public Integer getPresent() {
        return present;
    }

    public void setPresent(Integer present) {
        this.present = present;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setFacultyId(UUID facultyId) {
        this.facultyId = facultyId;
    }
}
