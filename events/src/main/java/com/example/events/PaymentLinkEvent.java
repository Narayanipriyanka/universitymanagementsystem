package com.example.events;

import java.util.UUID;

public class PaymentLinkEvent {
    private UUID studentId;
    private String paymentlink;
    private Double amount;

    public PaymentLinkEvent(UUID studentId, Double amount, String payLink) {
    this.studentId=studentId;
    this.paymentlink=payLink;
    this.amount=amount;}

    public UUID getStudentId() {
        return studentId;
    }

    public String getPaymentlink() {
        return paymentlink;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentlink(String paymentlink) {
        this.paymentlink = paymentlink;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
