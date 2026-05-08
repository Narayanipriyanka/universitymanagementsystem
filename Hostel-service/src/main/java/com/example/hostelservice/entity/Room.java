package com.example.hostelservice.entity;

import jakarta.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomNo;
    private HostelType type;
    private Integer noOfBeds;
    private Integer filledCount;
    @ManyToOne
    private Hostel hostel;

    public HostelType getType() {
        return type;
    }

    public void setType(HostelType type) {
        this.type = type;
    }

    public Integer getFilledCount() {
        return filledCount;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    public void setFilledCount(Integer filledCount) {
        this.filledCount = filledCount;
    }

    public Integer getNoOfBeds() {
        return noOfBeds;
    }

    public void setNoOfBeds(Integer noOfBeds) {
        this.noOfBeds = noOfBeds;
    }

    public Long getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Long roomNo) {
        this.roomNo = roomNo;
    }
}
