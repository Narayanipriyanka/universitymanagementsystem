package com.example.departmentservice.DTO;

import com.example.departmentservice.entity.BoughtFor;
import jakarta.persistence.Enumerated;

public class ResourceDTO {
    private String name;
    @Enumerated
    private BoughtFor type;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    private Integer count;
}
