package com.example.timtableservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomid;
    private String roomNo;
    private Integer floorNo;
    private Integer blockNo;
    private String program;
    private String dept;
    private Integer semester;
    private List<String> electronicresources;
    private List<String> resources;
    private Integer noOfResources;
    private Integer noofEResources;
    public Integer getFloorNo() {
        return floorNo;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Integer getNoofEResources() {
        return noofEResources;
    }

    public void setNoofEResources(Integer noofEResources) {
        this.noofEResources = noofEResources;
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

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Long getRoomid() {
        return roomid;
    }

    public Integer getNoOfResources() {
        return noOfResources;
    }

    public void setNoOfResources(Integer noOfResources) {
        this.noOfResources = noOfResources;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public List<String> getElectronicresources() {
        return electronicresources;
    }

    public void setElectronicresources(List<String> electronicresources) {
        this.electronicresources = electronicresources;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }
}
