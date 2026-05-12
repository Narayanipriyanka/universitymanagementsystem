package com.example.events;

import java.util.UUID;

public class OverDueAlert {
    private UUID studentId;
    private Double paid;
    private Double tutionFeeBalance;
    private Double examFeeBalance;
    private Double hostelFeeBalance;
    private Double messFeeBalance;
    private Double totalBalance;
    private Double recordFeeBalance;
    public OverDueAlert(UUID studentId, Double paid, Double tutionFeeBalance, Double examFeeBalance, Double hostelFeeBalance, Double recordFeeBalance, Double messFeeBalance, Double totalbalance) {
    this.studentId=studentId;
    this.paid=paid;
    this.hostelFeeBalance=hostelFeeBalance;
    this.totalBalance=totalbalance;
    this.tutionFeeBalance=tutionFeeBalance;
    this.examFeeBalance=examFeeBalance;
    this.recordFeeBalance=recordFeeBalance;
    this.messFeeBalance=messFeeBalance;}

    public Double getPaid() {
        return paid;
    }

    public Double getRecordFeeBalance() {
        return recordFeeBalance;
    }

    public void setRecordFeeBalance(Double recordFeeBalance) {
        this.recordFeeBalance = recordFeeBalance;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public Double getMessFeeBalance() {
        return messFeeBalance;
    }

    public void setMessFeeBalance(Double messFeeBalance) {
        this.messFeeBalance = messFeeBalance;
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

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
