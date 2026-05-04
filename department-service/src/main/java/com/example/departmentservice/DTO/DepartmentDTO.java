package com.example.departmentservice.DTO;

import java.util.UUID;

public class DepartmentDTO {
    private String departmentFullName;
    private String departmentCode;
    private UUID hodId;
    private String hodName;

    public String getDepartmentFullName() {
        return departmentFullName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public String getHodName() {
        return hodName;
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

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public void setDepartmentFullName(String departmentFullName) {
        this.departmentFullName = departmentFullName;
    }
}
