package com.yihang.serp.model;

import java.io.Serializable;

public class Type implements Serializable {
    private Integer id;

    private String typeName;

    public Type() {
    }

    public Type(String typeName) {
        this.typeName = typeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}