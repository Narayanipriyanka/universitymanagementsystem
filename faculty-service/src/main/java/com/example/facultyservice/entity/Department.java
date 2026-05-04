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
    private String degreecategory;
    @ManyToMany(mappedBy = "departments")
    private List<Faculty> faculties;

    public UUID getDeptId() {
        return deptId;
    }

    public String getDegreecategory() {
        return degreecategory;
    }

    public void setDegreecategory(String degreecategory) {
        this.degreecategory = degreecategory;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setDeptId(UUID deptId) {
        this.deptId = deptId;
    }
}
