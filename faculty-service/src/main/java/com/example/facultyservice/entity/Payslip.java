package com.example.facultyservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Payslip {
    @Id
    @GeneratedValue
    private Long id;
private String month;
private Double earnings;
private Double deductions;
private Double netPay;
private String transactionId;
@ManyToOne
@JsonIgnore
    private Faculty faculty;

    public Long getId() {
        return id;
    }

    public Double getNetPay() {
        return netPay;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setNetPay(Double netPay) {
        this.netPay = netPay;
    }

    public Double getDeductions() {
        return deductions;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
