package com.woniu.dao;

import cn.hutool.db.Db;
import com.woniu.entity.Product;
import com.woniu.entity.ProductType;
import com.woniu.jdbc.DbOpr;
import com.woniu.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {

    @Autowired
    private ProductTypeDao productTypeDao;

    //根据商品id查询商品数量
    public int showIdventoryByNum(int num){
        String sql="select * from shop_product where id=?";
        Product product= DbOpr.queryOne(sql, Product.class,num);
        return product.getInventory();
    }

    //通过id获取单个商品
    public Product getOneProduct(int id){
        String sql="select * from shop_product where id=?";
        Product product =DbOpr.queryOne(sql,Product.class,id);
        return product;
    }

    //获取所有商品的信息
    public List<Product> getAllProduct(){
        String sql="select * from shop_product";
        List<Product> list= DbOpr.query(sql,Product.class);
        return list;
    }

    //分页获取全部商品
    public MyPage<Product> selectAllLimit(int pageNum,int pageSize){
        String sql="select * from shop_product limit ?,?";
        //System.out.println("sql=" + sql + "|" + pageNum + "," + pageSize);
        MyPage<Product> myPage=new MyPage<Product>();
        List<Product> list=DbOpr.query(sql,Product.class,(pageNum-1)*pageSize,pageSize);
        //System.out.println(list.size());
        myPage.setList(list);
        String sqlcount="select count(*) from shop_product";
        int count=Integer.parseInt(DbOpr.queryVal(sqlcount).toString());
        int pagecount=count%pageSize==0?(count/pageSize):(count/pageSize+1);
        myPage.setPagecount(pagecount);
        return myPage;
    }

    //按类型分页获取商品
    public MyPage<Product> getProductByType(int pageNum,int pageSize,int tId){
        //System.out.println(tId);
        List<ProductType> listType=productTypeDao.getSonType(tId);
        MyPage<Product> myPage=new MyPage<Product>();
        List<Product> list=new ArrayList<>();
        for (ProductType productType : listType) {
            String sql="select * from shop_product where typeId=? limit ?,?";
            List<Product> listnow=DbOpr.query(sql,Product.class,productType.getId(),(pageNum-1)*pageSize,pageSize);
            for (Product product : listnow) {
                list.add(product);
            }
        }
        //System.out.println(list.size());
        myPage.setList(list);
        int countAll=0;
        for (ProductType productType : listType) {
            //System.out.println(productType.getId());
            String sqlcount="select count(*) from shop_product where typeId=?";
            Object obj=DbOpr.queryVal(sqlcount,productType.getId());
            int count=Integer.parseInt(obj.toString());
            //System.out.println(count);
            countAll+=count;
        }
        //System.out.println(countAll);
        int pagecount=countAll%pageSize==0?(countAll/pageSize):(countAll/pageSize+1);
        //System.out.println(pagecount);
        myPage.setPagecount(pagecount);
        return myPage;
    }

    //根据商品id修改商品数量
    public boolean changeProductNum(int pid,int num){
        String sql="select * from shop_product where id=?";
        Product product=DbOpr.queryOne(sql,Product.class,pid);
        if (product.getInventory()>=num){
            String changesql="update shop_product set inventory=? where id=?";
            DbOpr.excute(changesql,num,pid);
            return true;
        }
        return false;
    }
}
