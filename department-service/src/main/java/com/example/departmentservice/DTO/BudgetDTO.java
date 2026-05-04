package com.example.departmentservice.DTO;

public class BudgetDTO {
    private String depCode;
    private Integer year;
    private Double totalAmount;

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public Integer getYear() {
        return year;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
