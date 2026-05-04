package com.example.events;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class RegisterRequest {

    @NotBlank(message = "first name is required")
    public String firstName;
    @NotBlank(message = "last name is required")
    public String lastName;
    @Email(message="invalid email")
    @NotBlank(message="email should be required")
    public String email;
    @NotBlank(message="phone number required")
    @Pattern(regexp="^[0-9]{10}$",message="phone number must be 10 digits")
    public String phoneNum;
    @NotBlank(message = "Department is required")
    public String department;
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20)
    public String username;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    public String password;
    @NotBlank(message = "Role is required")
    @Pattern(regexp = "STUDENT|FACULTY", message = "Role must be STUDENT or FACULTY")
    public String role;
    @NotBlank(message = "Role is required")
    @Pattern(regexp = "MALE|FEMALE|OTHERS", message = " gender is invalid")
    public String gender;
    public Date dateOfBirth;

    public RegisterRequest() {
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
