package com.example.facultyservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Faculty {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstname;
    private String lastName;
    private String email;
    private Integer experience;
    private String position;
    private String gender;
    private String phoneNum;
    private Double salary;
    private Double pf;
    private Double tax;
    @OneToMany(mappedBy = "faculty",cascade= CascadeType.ALL,orphanRemoval=true)
    private List<Qualification> qualification;
    @OneToMany(mappedBy = "review",cascade= CascadeType.ALL,orphanRemoval=true)
    private List<Reviews> review;
    @ManyToMany
    @JoinTable(
            name = "faculty_departments",
            joinColumns = @JoinColumn(name = "faculty_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    @JsonIgnore
    private List<Department> departments;
    @OneToMany(mappedBy = "faculty",cascade= CascadeType.ALL,orphanRemoval=true)
    private List<Payslip> paySlips;
    @OneToMany(mappedBy = "faculty",cascade= CascadeType.ALL,orphanRemoval=true)
    private List<Course> courses;
    private String status;
    private Date joiningDate;
    private String username;
    private String password;
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getSalary() {
        return salary;
    }

    public List<Payslip> getPaySlip() {
        return paySlips;
    }

    public void setPaySlip(List<Payslip> paySlips) {
        this.paySlips = paySlips;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public void setQualification(List<Qualification> qualification) {
        this.qualification = qualification;
    }

    public List<Reviews> getReview() {
        return review;
    }

    public void setReview(List<Reviews> review) {
        this.review = review;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public List<Payslip> getPaySlips() {
        return paySlips;
    }

    public void setPaySlips(List<Payslip> paySlips) {
        this.paySlips = paySlips;
    }

    public Double getTax() {
        return tax;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<Qualification> getQualification() {
        return qualification;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getExperience() {
        return experience;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
public String getUsername(){
        return username;
}

    public void setUsername(String username) {
        this.username=username;
    }
}
