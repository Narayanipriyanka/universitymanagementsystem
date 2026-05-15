package com.example.events;

import java.util.UUID;

public class AttendanceReportEvent {
    private UUID facultyId;
    private String month;
    private Integer totalDays;
    private Integer present;
    private Integer absent;
    private Integer leave;
    private Integer attendancePercentage;
public AttendanceReportEvent(){

}
    public AttendanceReportEvent(Integer attendancePercentage, UUID facultyId, String month, Integer absent, Integer present, Integer totalDays, Integer leave) {
    this.facultyId=facultyId;
    this.month=month;
    this.totalDays=totalDays;
    this.present=present;
    this.absent=absent;
    this.attendancePercentage=attendancePercentage;
    this.leave=leave;}

    public UUID getFacultyId() {
        return facultyId;
    }

    public String getMonth() {
        return month;
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
