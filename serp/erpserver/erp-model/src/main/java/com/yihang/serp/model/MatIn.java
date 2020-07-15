package com.yihang.serp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MatIn {
    private Integer id;

    private Integer supplier;

    private Integer materiel;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date indate;

    private Integer store;

    private String storeadress;

    private String incnum;

    private Integer type;

    private String name;

    public PurSupplier getPurSuppliers() {
        return purSuppliers;
    }

    public void setPurSuppliers(PurSupplier purSuppliers) {
        this.purSuppliers = purSuppliers;
    }

    public Type getTypes() {
        return types;
    }

    public void setTypes(Type types) {
        this.types = types;
    }

    public Store getStores() {
        return stores;
    }

    public void setStores(Store stores) {
        this.stores = stores;
    }

    public Materiel getMateriels() {
        return materiels;
    }

    public void setMateriels(Materiel materiels) {
        this.materiels = materiels;
    }

    private PurSupplier purSuppliers;
    private Type types;
    private Store stores;
    private Materiel materiels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupplier() {
        return supplier;
    }

    public void setSupplier(Integer supplier) {
        this.supplier = supplier;
    }

    public Integer getMateriel() {
        return materiel;
    }

    public void setMateriel(Integer materiel) {
        this.materiel = materiel;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public String getStoreadress() {
        return storeadress;
    }

    public void setStoreadress(String storeadress) {
        this.storeadress = storeadress == null ? null : storeadress.trim();
    }

    public String getIncnum() {
        return incnum;
    }

    public void setIncnum(String incnum) {
        this.incnum = incnum == null ? null : incnum.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}