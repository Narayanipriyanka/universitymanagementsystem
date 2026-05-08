package com.example.hostelservice.dto;

import com.example.hostelservice.entity.HostelType;

public class HostelDTO {
    private HostelType type;
    private Integer noOfTRooms;
    private Double fee;

    public HostelType getType() {
        return type;
    }

    public Integer getNoOfTRooms() {
        return noOfTRooms;
    }

    public void setNoOfTRooms(Integer noOfTRooms) {
        this.noOfTRooms = noOfTRooms;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public void setType(HostelType type) {
        this.type = type;
    }
}
