package com.example.analyticsreportservice.dto;

public class DepartmentWiseReport {
    private Integer nooFStudentsPassed;
    private Integer nooFStudentFailed;
    private Double passPercentage;
    private Double failPercentage;
    private Integer totalStudents;
    private Integer noFStudentsTopped;

    public DepartmentWiseReport(Integer totalStudents, Integer nooFStudentsPassed, Integer nooFStudentFailed, Integer noFStudentsTopped, Double passPercentage, Double failPercentage) {
    this.failPercentage=failPercentage;
    this.totalStudents=totalStudents;
    this.nooFStudentFailed=nooFStudentFailed;
    this.nooFStudentsPassed=nooFStudentsPassed;
    this.passPercentage=passPercentage;
    this.noFStudentsTopped=noFStudentsTopped;}

    public Integer getNooFStudentsPassed() {
        return nooFStudentsPassed;
    }

    public Integer getNooFStudentFailed() {
        return nooFStudentFailed;
    }


    public Integer getNoFStudentsTopped() {
        return noFStudentsTopped;
    }

    public void setNoFStudentsTopped(Integer noFStudentsTopped) {
        this.noFStudentsTopped = noFStudentsTopped;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Double getFailPercentage() {
        return failPercentage;
    }

    public void setFailPercentage(Double failPercentage) {
        this.failPercentage = failPercentage;
    }

    public Double getPassPercentage() {
        return passPercentage;
    }

    public void setPassPercentage(Double passPercentage) {
        this.passPercentage = passPercentage;
    }

    public void setNooFStudentFailed(Integer nooFStudentFailed) {
        this.nooFStudentFailed = nooFStudentFailed;
    }

    public void setNooFStudentsPassed(Integer nooFStudentsPassed) {
        this.nooFStudentsPassed = nooFStudentsPassed;
    }
}
