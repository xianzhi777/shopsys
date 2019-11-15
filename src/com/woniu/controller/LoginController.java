package com.woniu.controller;

import com.woniu.entity.User;
import com.woniu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/log")
public class LoginController {
    @Autowired
    public UserService userService;

    @RequestMapping("/in")
    public String login(String account, String password, HttpSession session) {
        //System.out.println(account);
        //System.out.println(password);
        User user = userService.getUserByAccountAndPassword(account, password);
        if (user != null) {
            session.setAttribute("user", user);
            ServletContext application=session.getServletContext();
            application.setAttribute(user.getId()+"",session.getId());
            return "redirect:/home/main";
        }
        return "/login";
    }

    @RequestMapping("/out")
    public String logout(HttpSession session) {
        User user=(User) session.getAttribute("user");
        session.setAttribute("user", null);
        session.getServletContext().setAttribute(user.getId()+"",null);
        return "redirect:/home/main";
    }
}
