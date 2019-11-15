package com.woniu.service;

import cn.hutool.core.lang.func.VoidFunc1;
import cn.hutool.db.Db;
import com.woniu.dao.OneOrderDao;
import com.woniu.dao.OrderDao;
import com.woniu.dao.ProductDao;
import com.woniu.entity.OneOrder;
import com.woniu.entity.Order;
import com.woniu.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OneOrderDao oneOrderDao;
    @Autowired
    private ProductDao productDao;

    //将购物车修改为订单
    public boolean changeShoppingCarAsOrder(int uId, int aId, double total) {
        int id = orderDao.getShoppingCarIdByUid(uId);
        //System.out.println(id);
        if (id != -1) {
            orderDao.changeShoppingCarAsOrder(id, aId, total);
            return true;
        }
        return false;
    }

    //根据用户uId和state=1获取购物车编号 不存在返回-1
    public int getShoppingCarIdByUid(int uId) {
        int oId = orderDao.getShoppingCarIdByUid(uId);
        return oId;
    }


    //根据未支付订单显示所有的详情
    public List<OneOrder> getAllWithoutPayOneOrder(int uId) {
        List<Order> list = orderDao.getOrderIdIdByUid(uId);
        List<OneOrder> olist = new ArrayList<OneOrder>();
        for (Order order : list) {
            int oId = order.getId();
            List<OneOrder> oneList = oneOrderDao.getAllOneOrderByOrderId(oId);
            for (OneOrder oneOrder : oneList) {
                olist.add(oneOrder);
            }
        }
        return olist;
    }

    //根据用户id得到所有未支付订单id
    public List<Order> getAllOrderWithoutPay(int uId) {
        List<Order> list = orderDao.getOrderIdIdByUid(uId);
        return list;
    }

    //根据用户id得到所有未支付订单id
    public List<Order> getAllOrderhasPaid(int uId) {
        List<Order> list = orderDao.gethaspaidOrderIdIdByUid(uId);
        return list;
    }

    //根据未支付订单显示所有的详情
    public List<OneOrder> getAllHasPaidOneOrder(int uId) {
        List<Order> list = orderDao.gethaspaidOrderIdIdByUid(uId);
        List<OneOrder> olist = new ArrayList<OneOrder>();
        for (Order order : list) {
            int oId = order.getId();
            List<OneOrder> oneList = oneOrderDao.getAllOneOrderByOrderId(oId);
            for (OneOrder oneOrder : oneList) {
                olist.add(oneOrder);
            }
        }
        return olist;
    }

    //将未支付订单修改为已支付,同时减少商品库存
    public boolean payorderfin(int oid){
        List<OneOrder> list=oneOrderDao.getAllOneOrderByOrderId(oid);
        for (OneOrder oneOrder : list) {
            Product product=productDao.getOneProduct(oneOrder.getProductId());
            if(product.getInventory()<oneOrder.getBuyNum()){
                return false;
            }else {
                productDao.changeProductNum(oneOrder.getProductId(),product.getInventory()-oneOrder.getBuyNum());
            }
        }
        orderDao.payfin(oid);
        return true;
    }

    //根据orderid删除order和oneorder
    public void deleteOrderAndOneOrderById(int id){
        orderDao.deleteOrderAndOneOrderById(id);
    }
}
