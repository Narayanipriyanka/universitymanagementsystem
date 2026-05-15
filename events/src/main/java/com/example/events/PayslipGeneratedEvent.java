package com.example.events;

public class PayslipGeneratedEvent {
    private String email;
    private String month;
    private Double earnings;
    private Double deductions;
    private Double netPay;
public PayslipGeneratedEvent(){
    
}
    public PayslipGeneratedEvent(Double earnings, String month, Double deductions, Double netPay, String transactionId) {
    this.earnings=earnings;
    this.month=month;
    this.deductions=deductions;
    this.transactionId=transactionId;}

    public String getEmail() {
        return email;
    }

    public Double getDeductions() {
        return deductions;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Double getNetPay() {
        return netPay;
    }

    public void setNetPay(Double netPay) {
        this.netPay = netPay;
    }

    public String getMonth() {
        return month;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String transactionId;
}
