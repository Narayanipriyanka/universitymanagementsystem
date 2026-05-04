package com.example.departmentservice.entity;

import jakarta.persistence.*;

@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer year;
    private Double totalAmount;
    private Double usedAmount;
    private Double remainingAmount;
    @OneToOne
    @JoinColumn(name = "department_id", unique = true)
    private Department department;

    public Long getId() {
        return id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public Double getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(Double usedAmount) {
        this.usedAmount = usedAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
