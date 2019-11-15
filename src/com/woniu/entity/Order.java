package com.woniu.entity;

import java.sql.Timestamp;

public class Order {
    private int id;

    private String aliPayAccount;
    private int userId;
    private int addressId;
    private int state;
    private Timestamp updateTime;
    private Timestamp createTime;
    private int delete;
    private double total;

    public Order() {
    }

    public Order(int id,  String aliPayAccount, int userId, int addressId, int state, Timestamp updateTime, Timestamp createTime, int delete,double total) {
        this.id = id;
        this.aliPayAccount = aliPayAccount;
        this.userId = userId;
        this.addressId = addressId;
        this.state = state;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.delete = delete;
        this.total=total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getAliPayAccount() {
        return aliPayAccount;
    }

    public void setAliPayAccount(String aliPayAccount) {
        this.aliPayAccount = aliPayAccount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }


    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
