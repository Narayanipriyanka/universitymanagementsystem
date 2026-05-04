package com.example.departmentservice.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer duration;
    private Integer nofSems;
    @OneToMany(mappedBy = "program",cascade= CascadeType.ALL,orphanRemoval=true)
    private List<Department> departments;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Integer getNofSems() {
        return nofSems;
    }

    public void setNofSems(Integer nofSems) {
        this.nofSems = nofSems;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
