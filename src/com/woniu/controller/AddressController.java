package com.woniu.controller;


import com.woniu.entity.Address;
import com.woniu.entity.User;
import com.woniu.service.AddressService;
import com.woniu.service.OrderService;
import com.woniu.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private ShoppingCarService shoppingCarService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/show")
    public String showAddressPage(){

        return "address";
    }

    @RequestMapping("/all")
    @ResponseBody
    public List<Address> showAddressByUid(HttpSession session){
        User user=(User) session.getAttribute("user");
        List<Address> list=addressService.showAllAddressByUid(user.getId());
        return list;
    }

    @RequestMapping("/select")
    public String selectAddress(HttpSession session,int address){
        //System.out.println("addressId:"+address);
        if(address==-1){
            return "createaddress";
        }
        int uId=((User)session.getAttribute("user")).getId();
        int oId=orderService.getShoppingCarIdByUid(uId);
        double total=shoppingCarService.countTotalByOrderId(oId);
        //System.out.println("total:"+total);
        if (total!=-1){
            boolean flag=orderService.changeShoppingCarAsOrder(uId,address,total);
            //System.out.println(flag);
        }
        return "order";
    }

    @RequestMapping("/create")
    public String createNewAddress(HttpSession session,String n, String phone, String province, String city, String detail){
        User user=(User)session.getAttribute("user");
        addressService.createNewAddress(user.getId(),n,phone,province,city,detail);
        return "redirect:/address/show";
    }
}
