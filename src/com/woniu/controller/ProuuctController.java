package com.woniu.controller;

import com.woniu.entity.Product;
import com.woniu.service.ProductService;
import com.woniu.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProuuctController {
    @Autowired
    private ProductService productService;

    //获取所有商品数据返回给前端
    @RequestMapping("/all")
    @ResponseBody
    public List<Product> getAllProduct(){
        List<Product> productList=productService.getAllProduct();
        return productList;
    }

    //判断是否登录,登录后转到具体商品界面
    @RequestMapping("/one")
    public String showOneProduct(HttpServletRequest req, HttpSession session, int id){
        if(session.getAttribute("user")==null){
            return "redirect:/log/in";
        }
        Product product=productService.getOneProduct(id);
        if (product==null){
            return "redirect:/log/in";
        }
        req.setAttribute("product",product);
        req.setAttribute("id",id);
        return "product";
    }

    //分页获取全部商品
    @RequestMapping("/limit")
    @ResponseBody
    public MyPage<Product> getAllProductLimit(int pageNum,int pageSize){
        //System.out.println(pageNum);
        //System.out.println(pageSize);
        return productService.getAllProductLimit(pageNum,pageSize);
    }

    //分页按类型获取获取商品
    @RequestMapping("/type")
    @ResponseBody
    public MyPage<Product> getProductByType(int pageNum,int pageSize,int tid){
        return productService.getProductByTypeId(pageNum,pageSize,tid);
    }
}
