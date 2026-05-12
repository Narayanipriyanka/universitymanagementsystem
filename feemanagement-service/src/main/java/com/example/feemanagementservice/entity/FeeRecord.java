package com.example.feemanagementservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class FeeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID studentId;
    private Double totalbalance;
    private Double paid;
    private Double tutionFeeBalance;
    private Double examFeeBalance;
    private Double hostelFeeBalance;
    private Double recordFeeBalance;
    private Double messFeeBalance;
    public Long getId() {
        return id;
    }

    public Double getMessFeeBalance() {
        return messFeeBalance;
    }

    public void setMessFeeBalance(Double messFeeBalance) {
        this.messFeeBalance = messFeeBalance;
    }

    public Double getRecordFeeBalance() {
        return recordFeeBalance;
    }

    public void setRecordFeeBalance(Double recordFeeBalance) {
        this.recordFeeBalance = recordFeeBalance;
    }

    public Double getHostelFeeBalance() {
        return hostelFeeBalance;
    }

    public void setHostelFeeBalance(Double hostelFeeBalance) {
        this.hostelFeeBalance = hostelFeeBalance;
    }

    public Double getExamFeeBalance() {
        return examFeeBalance;
    }

    public void setExamFeeBalance(Double examFeeBalance) {
        this.examFeeBalance = examFeeBalance;
    }

    public Double getTutionFeeBalance() {
        return tutionFeeBalance;
    }

    public void setTutionFeeBalance(Double tutionFeeBalance) {
        this.tutionFeeBalance = tutionFeeBalance;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public Double getTotalbalance() {
        return totalbalance;
    }

    public void setTotalbalance(Double totalbalance) {
        this.totalbalance = totalbalance;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
