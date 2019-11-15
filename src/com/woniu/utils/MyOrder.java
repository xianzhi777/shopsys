package com.woniu.utils;

import com.woniu.entity.OneOrder;
import com.woniu.entity.Order;

import java.util.List;

public class MyOrder {
    private List<Order> orderList;
    private List<OneOrder> oneOrderList;

    public MyOrder() {
    }

    public MyOrder(List<Order> orderList, List<OneOrder> oneOrderList) {
        this.orderList = orderList;
        this.oneOrderList = oneOrderList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderIdList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<OneOrder> getOneOrderList() {
        return oneOrderList;
    }

    public void setOneOrderList(List<OneOrder> oneOrderList) {
        this.oneOrderList = oneOrderList;
    }
}
