package com.example.facultyservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class FacultyProfileDTO {

    @JsonProperty("facultyID")
    private UUID facultyID;
    private String facultyname;
    private String department;
    private String position;
   private Date joiningDate;
   private Double fixedsalary;
    private Double pf;
    private Double tax;

    private String status;

    public Double getTax() {
        return tax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getPf() {
        return pf;
    }

    public void setPf(Double pf) {
        this.pf = pf;
    }

    public UUID getFacultyID() {
        return facultyID;
    }

    public Double getFixedsalary() {
        return fixedsalary;
    }

    public void setFixedsalary(Double fixedsalary) {
        this.fixedsalary = fixedsalary;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFacultyname() {
        return facultyname;
    }

    public void setFacultyname(String facultyname) {
        this.facultyname = facultyname;
    }

    public void setFacultyID(UUID facultyID) {
        this.facultyID = facultyID;
    }
}
