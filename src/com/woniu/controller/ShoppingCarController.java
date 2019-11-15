package com.woniu.controller;

import com.woniu.entity.OneOrder;
import com.woniu.entity.User;
import com.woniu.service.ProductService;
import com.woniu.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buy")
public class ShoppingCarController {
    @Autowired
    private ShoppingCarService scs;

    @Autowired
    private ProductService productService;
    @RequestMapping("/car")
    //显示购物车页面
    public String showOrder(){
        return "/shoppingCar";
    }

    @RequestMapping("/something")
    @ResponseBody
    //根据商品的id和数量将商品添加到购物车
    public boolean  buySomething(Integer productid, Integer num, HttpSession session , HttpServletRequest req){
        User user=(User) session.getAttribute("user");
        return scs.buySomething(productid,num,user);
    }

    @RequestMapping("/show")
    @ResponseBody
    public List<OneOrder> showAllOneOrderINShoppingCar(HttpSession session){
        User user=(User) session.getAttribute("user");
        List<OneOrder> list=scs.showAllInShoppingcar(user.getId());
        return list;
    }

    @RequestMapping("/del")
    @ResponseBody
    public void deleteOneOrder(int id){
       scs.deleteOneOrderById(id);
    }

    @RequestMapping("/leave")
    public void changeNumWhenLeave(String arr){
        String[] strs=arr.split(",");
        List<OneOrder> list=new ArrayList();
        for (String str:strs) {
            if(!str.equals("")){
                String[] strs1=str.split(":");
                OneOrder oneo=new OneOrder();
                oneo.setId(Integer.parseInt(strs1[0]));
                oneo.setBuyNum(Integer.parseInt(strs1[1]));
                list.add(oneo);
            }
        }
        scs.changeBuyNumWhenLeave(list);
    }
}
