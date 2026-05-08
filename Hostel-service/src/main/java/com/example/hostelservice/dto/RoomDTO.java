package com.example.hostelservice.dto;

import com.example.hostelservice.entity.HostelType;

public class RoomDTO {
    private HostelType type;
    private Integer noOfBeds;
    private Integer filledCount;
    private Long hostelId;

    public HostelType getType() {
        return type;
    }

    public Integer getNoOfBeds() {
        return noOfBeds;
    }

    public Integer getFilledCount() {
        return filledCount;
    }

    public Long getHostelId() {
        return hostelId;
    }

    public void setHostelId(Long hostelId) {
        this.hostelId = hostelId;
    }

    public void setFilledCount(Integer filledCount) {
        this.filledCount = filledCount;
    }

    public void setNoOfBeds(Integer noOfBeds) {
        this.noOfBeds = noOfBeds;
    }

    public void setType(HostelType type) {
        this.type = type;
    }
}
