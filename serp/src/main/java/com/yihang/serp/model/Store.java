package com.yihang.serp.model;

import java.io.Serializable;

public class Store implements Serializable {
    private Integer id;

    private String storeName;

    public Store() {
    }

    public Store(String storeName) {
        this.storeName = storeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }
}