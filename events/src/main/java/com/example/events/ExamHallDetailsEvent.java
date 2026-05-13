package com.example.events;

import java.util.UUID;

public class ExamHallDetailsEvent {
    private UUID studentId;
    private String hallNo;
    private Long seatNo;
    private Integer floorNo;

    public ExamHallDetailsEvent(UUID studentId, String hallAllocated, Long seatAllocated, Integer floor) {
    this.studentId=studentId;
    this.hallNo=hallAllocated;
    this.floorNo=floor;
    this.seatNo=seatAllocated;}

    public UUID getStudentId() {
        return studentId;
    }

    public String getHallNo() {
        return hallNo;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    public Long getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Long seatNo) {
        this.seatNo = seatNo;
    }

    public void setHallNo(String hallNo) {
        this.hallNo = hallNo;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}
