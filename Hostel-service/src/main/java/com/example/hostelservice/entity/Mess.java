package com.example.hostelservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
import java.util.UUID;

@Entity
public class Mess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<UUID> studentIds;
    private double monthlyFee;
    private MessType type;
    private Boolean onlyForHostelers;
    public Long getId() {
        return id;
    }

    public Boolean getOnlyForHostelers() {
        return onlyForHostelers;
    }

    public void setOnlyForHostelers(Boolean onlyForHostelers) {
        this.onlyForHostelers = onlyForHostelers;
    }

    public MessType getType() {
        return type;
    }

    public void setType(MessType type) {
        this.type = type;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public List<UUID> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<UUID> studentIds) {
        this.studentIds = studentIds;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
