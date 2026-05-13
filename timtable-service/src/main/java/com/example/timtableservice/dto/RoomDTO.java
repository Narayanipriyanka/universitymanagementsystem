package com.example.timtableservice.dto;

public class RoomDTO {
    private String roomNo;
    private Integer floorNo;
    private Integer blockNo;
    private String program;
    private String dept;
    private String semester;
    public String getRoomNo() {
        return roomNo;
    }

    public String getProgram() {
        return program;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public Integer getBlockNo() {
        return blockNo;
    }

    public void setBlockNo(Integer blockNo) {
        this.blockNo = blockNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
}
