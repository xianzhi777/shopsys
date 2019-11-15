package com.woniu.entity;

public class OneOrder {
    private int id;
    private int orderId;
    private String name;
    private double price;
    private String img;
    private int buyNum;
    private int delete;
    private int productId;

    public OneOrder(){

    }

    public OneOrder(int id, int productId,int orderId, String name, double price, String img, int buyNum, int delete) {
        this.id = id;
        this.orderId = orderId;
        this.name = name;
        this.price = price;
        this.img = img;
        this.buyNum = buyNum;
        this.delete = delete;
        this.productId=productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
