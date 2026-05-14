package com.example.analyticsreportservice.dto;

public class StudentPerformanceDTO {
    private Integer totalStudents;
    private Integer totalpassedStudents;
    private Integer totalFailedStudents;
    private Integer passPercentage;
    private Integer failurePercentage;
    private Integer totalAGrades;
    private String overAllPerformnce;

    public StudentPerformanceDTO(Integer totalpassedStudents, Integer totalStudents, Integer totalFailedStudents, Integer totalAGrades, Integer passPercentage, Integer failurePercentage, String overall) {
    this.totalpassedStudents=totalpassedStudents;
    this.failurePercentage=failurePercentage;
    this.totalAGrades=totalAGrades;
    this.totalFailedStudents=totalFailedStudents;
    this.overAllPerformnce=overall;
    this.passPercentage=passPercentage;
    this.totalStudents=totalStudents;}

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public String getOverAllPerformnce() {
        return overAllPerformnce;
    }

    public void setOverAllPerformnce(String overAllPerformnce) {
        this.overAllPerformnce = overAllPerformnce;
    }

    public String getOverAll() {
        return overAll;
    }

    public void setOverAll(String overAll) {
        this.overAll = overAll;
    }

    public Integer getTotalFailedStudents() {
        return totalFailedStudents;
    }

    public void setTotalFailedStudents(Integer totalFailedStudents) {
        this.totalFailedStudents = totalFailedStudents;
    }

    public Integer getTotalpassedStudents() {
        return totalpassedStudents;
    }

    public Integer getTotalAGrades() {
        return totalAGrades;
    }

    public void setTotalAGrades(Integer totalAGrades) {
        this.totalAGrades = totalAGrades;
    }

    public Integer getFailurePercentage() {
        return failurePercentage;
    }

    public void setFailurePercentage(Integer failurePercentage) {
        this.failurePercentage = failurePercentage;
    }

    public Integer getPassPercentage() {
        return passPercentage;
    }

    public void setPassPercentage(Integer passPercentage) {
        this.passPercentage = passPercentage;
    }

    public void setTotalpassedStudents(Integer totalpassedStudents) {
        this.totalpassedStudents = totalpassedStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }
}
