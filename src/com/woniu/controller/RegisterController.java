package com.woniu.controller;

import com.woniu.service.UserService;
import com.woniu.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    //跳转到注册页面
    @RequestMapping("/in")
    public String jumpToRegister(){
        return "/register";
    }

    //用户注册
    @RequestMapping("/insert")
    public String register(String account,String password){
        if(Utils.ckeckString(account)&&Utils.checkStringLength(password)){
            if(!userService.ifUserExist(account)){
                userService.createUserByAccountAndPassword(account,password);
                return "redirect:/log/in";
            }
        }
        return "/register";
    }
}
