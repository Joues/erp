package com.yihang.serp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MatOut {
    private Integer id;

    private Integer materiel;

    private Integer supplier;

    private Integer type;

    private Integer store;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date outdate;

    private String rednum;

    private String other;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMateriel() {
        return materiel;
    }

    public void setMateriel(Integer materiel) {
        this.materiel = materiel;
    }

    public Integer getSupplier() {
        return supplier;
    }

    public void setSupplier(Integer supplier) {
        this.supplier = supplier;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getOutdate() {
        return outdate;
    }

    public void setOutdate(Date outdate) {
        this.outdate = outdate;
    }

    public String getRednum() {
        return rednum;
    }

    public void setRednum(String rednum) {
        this.rednum = rednum == null ? null : rednum.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }
}