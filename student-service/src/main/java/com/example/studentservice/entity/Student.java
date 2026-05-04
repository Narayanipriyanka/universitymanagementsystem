package com.example.studentservice.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String email;
    private Date dateOfBirth;
    private String username;
    private String password;
    private String bloodGroup;
    private String photoPath;
    @Enumerated(EnumType.STRING)
    private StudentIDcardStatus status;
    @OneToMany(mappedBy = "parent",cascade= CascadeType.ALL,orphanRemoval=true)
    private List<Parent> parents;
    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus;
    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setStatus(StudentIDcardStatus status) {
        this.status = status;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Integer getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(Integer currentSemester) {
        this.currentSemester = currentSemester;
    }



    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }


    public String getAddress() {
        return address;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private String gender;

    private String address;


    private LocalDate admissionDate;

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    private String department;
    private Integer currentYear;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String program;
    private String userId;
    private Integer currentSemester;

    public Integer getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    public StudentIDcardStatus getStatus() {
        return status;
    }

    public void setIdCardPath(String filePath) {
    }
}
