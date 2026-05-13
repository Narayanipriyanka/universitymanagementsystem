package com.example.examservice.dto;

public class ExamHallDTO {
    private String hallNo;
    private Integer hallCapacity;
    private Integer floor;

    public String getHallNo() {
        return hallNo;
    }

    public Integer getHallCapacity() {
        return hallCapacity;
    }

    public void setHallCapacity(Integer hallCapacity) {
        this.hallCapacity = hallCapacity;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setHallNo(String hallNo) {
        this.hallNo = hallNo;
    }
}
