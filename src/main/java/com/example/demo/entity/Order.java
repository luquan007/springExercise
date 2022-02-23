package com.example.demo.entity;

import lombok.Data;

@Data
public class Order {

    private Integer id;

    private String name;

    private String idStr;

    public String getIdStr() {
        if (id != null) idStr = id.toString();
        return idStr;
    }

    public void setIdStr(String idStr) {
        if (id != null) idStr = id.toString();
        this.idStr = idStr;
    }

    public Order(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
