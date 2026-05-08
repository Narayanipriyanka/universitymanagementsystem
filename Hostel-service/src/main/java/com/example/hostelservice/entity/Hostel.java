package com.example.hostelservice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private HostelType type;
    private Integer noOfTRooms;
    private Double fee;
    @OneToMany(mappedBy = "hostel")
    private List<Room> rooms;

    public Long getId() {
        return id;
    }

    public HostelType getType() {
        return type;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Integer getNoOfTRooms() {
        return noOfTRooms;
    }

    public void setNoOfTRooms(Integer noOfTRooms) {
        this.noOfTRooms = noOfTRooms;
    }

    public void setType(HostelType type) {
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
