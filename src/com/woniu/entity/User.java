package com.woniu.entity;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    private int id;
    private String account;
    private String password;
    private Timestamp updateTime;
    private Timestamp createTime;
    private int delete;

    public User() {
    }

    public User(int id, String account, String password, Timestamp updateTime, Timestamp createTime, int delete) {
        this.id = id;
        this.account = account;
        this.password = password;

        this.updateTime = updateTime;
        this.createTime = createTime;
        this.delete = delete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }
}
