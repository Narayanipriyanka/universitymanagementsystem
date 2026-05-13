package com.example.examservice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ExamHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hallNo;
    private Integer hallCapacity;
    private Integer floor;
    private Integer totalNoOfallocated;
    @OneToMany( mappedBy = "examHall", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Seat> seatList;
    private Boolean isAvailable;

    public String getHallNo() {
        return hallNo;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public Integer getTotalNoOfallocated() {
        return totalNoOfallocated;
    }

    public void setTotalNoOfallocated(Integer totalNoOfallocated) {
        this.totalNoOfallocated = totalNoOfallocated;
    }

    public Integer getHallCapacity() {
        return hallCapacity;
    }

    public void setHallCapacity(Integer hallCapacity) {
        this.hallCapacity = hallCapacity;
    }

    public void setHallNo(String hallNo) {
        this.hallNo = hallNo;
    }
}
