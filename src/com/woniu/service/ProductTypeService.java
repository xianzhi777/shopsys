package com.woniu.service;


import com.woniu.dao.ProductTypeDao;
import com.woniu.entity.ProductType;
import com.woniu.jdbc.DbOpr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeDao productTypeDao;
    //获得所有商品类型
    public List<ProductType> showAllProductType(){
        return productTypeDao.showAllProductType();
    }
}
