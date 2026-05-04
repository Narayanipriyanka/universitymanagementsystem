package com.example.departmentservice.DTO;

import java.util.List;
import java.util.UUID;

public class ProgramDTO {
    private String name;
    private List<UUID> department_ids;
    private Integer duration;

    public String getName() {
        return name;
    }

    public List<UUID> getDepartment_ids() {
        return department_ids;
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

    public void setDepartment_ids(List<UUID> department_ids) {
        this.department_ids = department_ids;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer nofSems;
}
