package com.example.hostelservice.dto;

import com.example.hostelservice.entity.MessType;

import java.util.List;
import java.util.UUID;

public class MessDTO {
    private double monthlyFee;
    private MessType type;
    private Boolean onlyForHostelers;

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public MessType getType() {
        return type;
    }

    public Boolean getOnlyForHostelers() {
        return onlyForHostelers;
    }

    public void setOnlyForHostelers(Boolean onlyForHostelers) {
        this.onlyForHostelers = onlyForHostelers;
    }

    public void setType(MessType type) {
        this.type = type;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
}
