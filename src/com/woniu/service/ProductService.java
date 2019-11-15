package com.woniu.service;

import com.woniu.dao.ProductDao;
import com.woniu.entity.Product;
import com.woniu.jdbc.DbOpr;
import com.woniu.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class ProductService {
   @Autowired
   private ProductDao productDao;

   //查询商品数量(商品id)
    public int showInventoryWithId(int pid){
        return productDao.showIdventoryByNum(pid);
    }

    //通过id获取单个商品
    public Product getOneProduct(int id){
        return productDao.getOneProduct(id);
    }

    //获取所有商品的信息
    public List<Product> getAllProduct(){
        return productDao.getAllProduct();
    }

    //分页获取全部商品
    public MyPage<Product> getAllProductLimit(int pageNum,int pageSize){
        return productDao.selectAllLimit(pageNum,pageSize);
    }

    //分页获取分类的商品
    public MyPage<Product> getProductByTypeId(int pageNum,int pageSize,int tId){
        return productDao.getProductByType(pageNum,pageSize,tId);
    }

    //修改商品库存
    public boolean changeProductInventory(int pid,int num){
        return productDao.changeProductNum(pid,num);
    }
}
