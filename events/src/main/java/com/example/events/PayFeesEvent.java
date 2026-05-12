package com.example.events;

import java.util.UUID;

public class PayFeesEvent {
    private UUID studentId;
    private Double amount;
    private String payingFor;
    private Integer semester;

    public PayFeesEvent(UUID studentId, Double fine, String s) {
        this.studentId=studentId;
        this.amount=fine;
        this.payingFor=s;

    }

    public UUID getStudentId() {
        return studentId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getPayingFor() {
        return payingFor;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setPayingFor(String payingFor) {
        this.payingFor = payingFor;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
