package com.example.analyticsreportservice.dto;

public class AttendanceReportDetails {
    private Integer totalFaculty;
    private Integer leastAttendnace;
    private Integer totalLeastAttendanceFaculties;
    private Double avgAttendancePercentage;
    private Double leavePercentage;
    private Integer totalLeaves;
    private Double absentPercentage;
    private Integer highestAttendnance;
    private Integer noofHighestattendnaceFaculties;

    public AttendanceReportDetails(Integer totalFaculty,Integer leastAttendnace, Integer totalLeastAttendanceFaculties, Double avgAttendancePercentage,Integer highestattendance, Integer noofHighestattendnaceFaculties, Double absentPercentage, Double leavePercentage, Integer totalLeaves) {
        this.totalFaculty = totalFaculty;
        this.totalLeastAttendanceFaculties = totalLeastAttendanceFaculties;
        this.avgAttendancePercentage = avgAttendancePercentage;
        this.leavePercentage = leavePercentage;
        this.absentPercentage = absentPercentage;
        this.noofHighestattendnaceFaculties = noofHighestattendnaceFaculties;
        this.totalLeaves = totalLeaves;
        this.leastAttendnace=leastAttendnace;
        this.highestAttendnance=highestattendance;
    }

    public Integer getHighestAttendnance() {
        return highestAttendnance;
    }

    public void setHighestAttendnance(Integer highestAttendnance) {
        this.highestAttendnance = highestAttendnance;
    }

    public Integer getLeastAttendnace() {
        return leastAttendnace;
    }

    public void setLeastAttendnace(Integer leastAttendnace) {
        this.leastAttendnace = leastAttendnace;
    }

    public Integer getTotalFaculty() {
        return totalFaculty;
    }

    public Integer getNoofHighestattendnaceFaculties() {
        return noofHighestattendnaceFaculties;
    }

    public void setNoofHighestattendnaceFaculties(Integer noofHighestattendnaceFaculties) {
        this.noofHighestattendnaceFaculties = noofHighestattendnaceFaculties;
    }

    public Integer getTotalLeastAttendanceFaculties() {
        return totalLeastAttendanceFaculties;
    }

    public Double getAbsentPercentage() {
        return absentPercentage;
    }

    public void setAbsentPercentage(Double absentPercentage) {
        this.absentPercentage = absentPercentage;
    }

    public Integer getTotalLeaves() {
        return totalLeaves;
    }

    public void setTotalLeaves(Integer totalLeaves) {
        this.totalLeaves = totalLeaves;
    }

    public Double getLeavePercentage() {
        return leavePercentage;
    }

    public void setLeavePercentage(Double leavePercentage) {
        this.leavePercentage = leavePercentage;
    }

    public Double getAvgAttendancePercentage() {
        return avgAttendancePercentage;
    }

    public void setAvgAttendancePercentage(Double avgAttendancePercentage) {
        this.avgAttendancePercentage = avgAttendancePercentage;
    }

    public void setTotalLeastAttendanceFaculties(Integer totalLeastAttendanceFaculties) {
        this.totalLeastAttendanceFaculties = totalLeastAttendanceFaculties;
    }

    public void setTotalFaculty(Integer totalFaculty) {
        this.totalFaculty = totalFaculty;
    }
}
