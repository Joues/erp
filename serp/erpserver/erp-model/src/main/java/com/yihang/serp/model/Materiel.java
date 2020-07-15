package com.yihang.serp.model;

public class Materiel {
    private Integer id;

    private String materiel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMateriel() {
        return materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel == null ? null : materiel.trim();
    }
}