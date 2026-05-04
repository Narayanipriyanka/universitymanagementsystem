package com.example.facultyservice.dto;

import java.util.UUID;

public class PaySlipDTO {
    private UUID facultyId;
    private String month;
    private String transactionId;
    public UUID getFacultyId() {
        return facultyId;
    }

    public String getMonth() {
        return month;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    public void setMonth(String month) {
        this.month = month;
    }

    public void setFacultyId(UUID facultyId) {
        this.facultyId = facultyId;
    }
}
