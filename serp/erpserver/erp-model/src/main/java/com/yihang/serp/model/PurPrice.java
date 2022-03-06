package com.yihang.serp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PurPrice {
    private Integer id;

    private Integer supplier;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date date;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date aboutdate;

    private String price;

    private String tax;

    private String acount;

    private Integer state;

    private String amount;

    public PurSupplier getPurSuppliers() {
        return purSuppliers;
    }

    public void setPurSuppliers(PurSupplier purSuppliers) {
        this.purSuppliers = purSuppliers;
    }

    public State getStates() {
        return states;
    }

    public void setStates(State states) {
        this.states = states;
    }

    private PurSupplier purSuppliers;
    private State states;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getAboutdate() {
        return aboutdate;
    }

    public void setAboutdate(Date aboutdate) {
        this.aboutdate = aboutdate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax == null ? null : tax.trim();
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount == null ? null : acount.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }
}