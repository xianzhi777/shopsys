package com.woniu.controller;

import com.woniu.entity.ProductType;
import com.woniu.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/main")
    public String getMain(Model model){
        List<ProductType> list=productTypeService.showAllProductType();
        model.addAttribute("list",list);
        return "/home";
    }


}
