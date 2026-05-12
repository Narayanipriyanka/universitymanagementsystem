package com.example.events;

import java.time.LocalDate;
import java.util.UUID;

public class FeeInvoiceEvent {
    private UUID studentId;
    private LocalDate paidDate;
    private String category;
    private Double amountPaid;
    private Double balance;
    private Double totalBalance;

    public FeeInvoiceEvent(UUID studentId, String category, Double amount, Double categoryBalance, Double totalbalance,LocalDate paidDate) {
    this.studentId=studentId;
    this.totalBalance=totalbalance;
    this.amountPaid=amount;
    this.category=category;
    this.paidDate=paidDate;
    this.balance=categoryBalance;}

    public UUID getStudentId() {
        return studentId;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
