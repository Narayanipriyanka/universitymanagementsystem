package com.example.departmentservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private UUID deptId;
    private String departmentFullName;
    private String departmentCode;
    private UUID hodId;
    private String hodName;
@ManyToOne
@JoinColumn(name="program_id")
@JsonIgnore
private Program program;

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public UUID getDeptId() {
        return deptId;
    }


    public String getHodName() {
        return hodName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentFullName() {
        return departmentFullName;
    }

    public void setDepartmentFullName(String departmentFullName) {
        this.departmentFullName = departmentFullName;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }

    public UUID getHodId() {
        return hodId;
    }

    public void setHodId(UUID hodId) {
        this.hodId = hodId;
    }


    public String getDepartmentName() {
        return departmentFullName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentFullName = departmentName;
    }

    public void setDeptId(UUID deptId) {
        this.deptId = deptId;
    }
}
