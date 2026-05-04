package com.example.studentservice.dto;

import java.util.UUID;

public class StudentRegisterDTO {
    public UUID studentID;
    private String fullname;
    private String fatherName;
    private String email;
    private String gender;

    public UUID getStudentID() {
        return studentID;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setStudentID(UUID studentID) {
        this.studentID = studentID;
    }

    private String address;
    private String bloodGroup;
    private String phone;
}
