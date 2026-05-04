package com.example.attendanceservice.dto;

import java.time.LocalDate;
import java.util.UUID;

public class LeaveDTO {
    private UUID facultyId;
    private String leaveReason;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer noOfDays;

    public UUID getFacultyId() {
        return facultyId;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public Integer getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public void setFacultyId(UUID facultyId) {
        this.facultyId = facultyId;
    }
}
