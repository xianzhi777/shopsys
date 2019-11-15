package com.woniu.controller;

import com.woniu.entity.User;
import com.woniu.service.OrderService;
import com.woniu.utils.MyOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired


    @RequestMapping("/show")
    public String submitorder() {
        return "order";
    }

    @RequestMapping("/withoutpay")
    @ResponseBody
    public MyOrder showWithoutPayList(HttpSession session){
        User user=(User)session.getAttribute("user");
        int uId=user.getId();
        MyOrder myOrder=new MyOrder();
        myOrder.setOrderIdList(orderService.getAllOrderWithoutPay(uId));
        myOrder.setOneOrderList(orderService.getAllWithoutPayOneOrder(uId));
        return myOrder;
    }

    @RequestMapping("/haspaid")
    @ResponseBody
    public MyOrder showhasPaidList(HttpSession session){
        User user=(User)session.getAttribute("user");
        int uId=user.getId();
        MyOrder myOrder=new MyOrder();
        myOrder.setOrderIdList(orderService.getAllOrderhasPaid(uId));
        myOrder.setOneOrderList(orderService.getAllHasPaidOneOrder(uId));
        return myOrder;
    }

    @RequestMapping("/del")
    @ResponseBody
    public void deleteOrderById(int id){
        orderService.deleteOrderAndOneOrderById(id);
    }
}
