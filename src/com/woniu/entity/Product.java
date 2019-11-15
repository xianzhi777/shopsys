package com.woniu.entity;

import com.sun.jna.platform.win32.Sspi;

import java.sql.Timestamp;

public class Product {
    private int id;
    private String name;
    private int typeId;
    private double price;
    private String img;
    private String detail;
    private Timestamp createTime;
    private int state;
    private int inventory;

    public Product() {
    }

    public Product(int id, String name, int typeId, double price, String img, String text, Timestamp createTime, int state,int inventory) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.price = price;
        this.img = img;
        this.detail = text;
        this.createTime = createTime;
        this.state = state;
        this.inventory=inventory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
