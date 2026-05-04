package com.example.departmentservice.entity;

import jakarta.persistence.*;

@Entity
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated
    private BoughtFor type;
    private Double price;
    private Integer count;
    @OneToOne
    private Department allocatedDept;
    @Enumerated
    private AllocationStatus status;

    public Department getAllocatedDept() {
        return allocatedDept;
    }

    public void setAllocatedDept(Department allocatedDept) {
        this.allocatedDept = allocatedDept;
    }

    public Integer getCount() {
        return count;
    }

    public AllocationStatus getStatus() {
        return status;
    }

    public void setStatus(AllocationStatus status) {
        this.status = status;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BoughtFor getType() {
        return type;
    }

    public void setType(BoughtFor type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
