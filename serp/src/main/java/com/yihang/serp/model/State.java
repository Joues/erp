package com.yihang.serp.model;

import java.io.Serializable;

public class State implements Serializable {
    private Integer id;

    private String stateName;

    public State() {
    }

    public State(String stateName) {
        this.stateName = stateName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}