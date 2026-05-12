package com.example.feemanagementservice.dto;

import com.example.feemanagementservice.entity.PaymentFor;
import com.example.feemanagementservice.entity.PaymentMethod;

import java.util.UUID;

public class PaymentDTO {
    private UUID studentId;
    private Integer semester;
    private String program;
    private Double amount;
    private PaymentFor category;
    private PaymentMethod paymentMethod;

    public UUID getStudentId() {
        return studentId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public PaymentFor getCategory() {
        return category;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setCategory(PaymentFor category) {
        this.category = category;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
