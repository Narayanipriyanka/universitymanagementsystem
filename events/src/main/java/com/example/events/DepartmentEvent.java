package com.example.events;

public class DepartmentEvent {
    private String departmentName;
    private String degreeCategory;

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDegreeCategory() {
        return degreeCategory;
    }

    public void setDegreeCategory(String degreeCategory) {
        this.degreeCategory = degreeCategory;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
