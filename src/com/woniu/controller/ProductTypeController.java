package com.woniu.controller;

import com.woniu.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/type")
public class ProductTypeController {

    @Autowired
    public ProductTypeService productTypeService;

    @RequestMapping("/product")
    @ResponseBody
    public List showAllProductType(){
        List list=productTypeService.showAllProductType();
        return list;
    }

    @RequestMapping("/showbyselect")
    @ResponseBody
    public void showProductBySelect(int tid){

    }
}
