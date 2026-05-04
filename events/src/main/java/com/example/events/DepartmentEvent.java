package com.example.events;

import java.util.UUID;

public class DepartmentEvent {
    private UUID deptId;
    private String departmentFullName;
    private String departmentCode;
    private UUID hodId;
    private String hodName;
    public DepartmentEvent() {
    }
    public DepartmentEvent(String departmentFullName, String departmentCode, UUID hodId, String hodName) {
    this.departmentCode=departmentCode;
    this.departmentFullName=departmentFullName;
    this.hodId=hodId;
    this.hodName=hodName;}

    public String getDepartmentFullName() {
        return departmentFullName;
    }

    public UUID getDeptId() {
        return deptId;
    }

    public void setDeptId(UUID deptId) {
        this.deptId = deptId;
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
