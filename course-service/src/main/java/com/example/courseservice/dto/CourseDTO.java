package com.example.courseservice.dto;

public class CourseDTO {
    private String name;
    private String code;
    private Long credits;
    private String departmentCode;
    private String programCode;
    private Integer sem;

    public String getName() {
        return name;
    }

    public Integer getSem() {
        return sem;
    }

    public void setSem(Integer sem) {
        this.sem = sem;
    }

    public String getCode() {
        return code;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public Long getCredits() {
        return credits;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
