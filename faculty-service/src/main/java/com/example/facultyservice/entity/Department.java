package com.example.facultyservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private UUID deptId;
    private String departmentName;
    private String departmentCode;
    private String hodName;
    private UUID hodId;
    @ManyToMany(mappedBy = "departments")
    private List<Faculty> faculties;

    public UUID getDeptId() {
        return deptId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public UUID getHodId() {
        return hodId;
    }

    public void setHodId(UUID hodId) {
        this.hodId = hodId;
    }

    public String getHodName() {
        return hodName;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public void setDeptId(UUID deptId) {
        this.deptId = deptId;
    }
}
