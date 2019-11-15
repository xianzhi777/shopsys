package com.woniu.dao;

import com.woniu.entity.ProductType;
import com.woniu.jdbc.DbOpr;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductTypeDao {

    public List<ProductType> showAllProductType(){
        String sql="select * from shop_product_type";
        List<ProductType> list= DbOpr.query(sql,ProductType.class);
        return list;
    }

    //根据商品类型获得子类型
    public List<ProductType> getSonType(int id){
        String sql="select a.id,a.name,a.pid \n" +
                "from shop_product_type a\n" +
                "LEFT JOIN shop_product_type b\n" +
                "on a.pid=b.id\n" +
                "LEFT JOIN shop_product_type c\n" +
                "ON b.pid=c.id\n" +
                "WHERE a.id=? or b.id=? or c.id=?";
       List<ProductType> list= DbOpr.query(sql,ProductType.class,id,id,id);
       return list;
    }

}
