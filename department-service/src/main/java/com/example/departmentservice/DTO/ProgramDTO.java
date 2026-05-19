package com.example.departmentservice.DTO;

import java.util.List;
import java.util.UUID;

public class ProgramDTO {
    private String name;
    private List<String> departmentCodes;
    private Integer duration;
    private String programCode;
    public String getName() {
        return name;
    }

    public List<String> getDepartmentCodes() {
        return departmentCodes;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getNofSems() {
        return nofSems;
    }

    public void setNofSems(Integer nofSems) {
        this.nofSems = nofSems;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setDepartmentCodes(List<String> departmentCodes) {
        this.departmentCodes = departmentCodes;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer nofSems;
}
