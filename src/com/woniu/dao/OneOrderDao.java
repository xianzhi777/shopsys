package com.woniu.dao;

import com.woniu.entity.OneOrder;
import com.woniu.entity.Product;
import com.woniu.jdbc.DbOpr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OneOrderDao {
    @Autowired
    private ProductDao productDao;

    public  void addOneOrderBypIdAndNumAndOrderId(int pId,int num,int oId){
        String sql="insert into shop_oneorder" +
                " (orderId,productId,name,price,img,buyNum,`delete`)" +
                " values" +
                " (?,?,?,?,?,?,1)";
        Product product=productDao.getOneProduct(pId);
        DbOpr.excute(sql,oId,product.getId(),product.getName(),product.getPrice(),product.getImg(),num);
    }

    //根据Orderid和productId检测要购买的商品是否已经存在在购物车中
    public boolean ifProductInShoppingCar(int oId,int productId){
        String sql="select * from shop_oneorder  where orderId=? and productId=?";
        OneOrder oneOrder=DbOpr.queryOne(sql, OneOrder.class,oId,productId);
        //System.out.println("in");
        if(oneOrder!=null){
            return true;
        }
        return false;
    }

    //修改一条订单中的商品数量
    public void changeNumInOneOrder(int id,int num){
        String sql="update shop_oneorder" +
                " set buyNum=?" +
                " where id=?";
        DbOpr.excute(sql,num,id);
    }

    //根据OneOrderId获取购买数量
    public  int getBuyNumById(int id){
        String sql="select * from shop_oneorder where id=?";
        OneOrder oneOrder=DbOpr.queryOne(sql,OneOrder.class,id);
        return oneOrder.getBuyNum();
    }

    //根据productId,orderId获取当前商品购买的id
    public int getIdByPidAndOid(int pId,int oId){
        //System.out.println(pId);
        String sql="select * from shop_oneorder where productId=? and orderId=?";
        List<OneOrder> list=DbOpr.query(sql,OneOrder.class,pId,oId);
        if(list.size()==1){
            return list.get(0).getId();
        }
        return -1;
    }

    //根据OrderId获取所有OneOrder
    public List<OneOrder> getAllOneOrderByOrderId(int oId){
        String sql="select * from shop_oneorder where orderId=?";
        List<OneOrder> list=DbOpr.query(sql,OneOrder.class,oId);
        //System.out.println(list.size());
        return list;
    }

    //根据id删除一条OneOrder
    public void deleteOneOrderById(int id){
        //System.out.println(id);
        String sql="delete from shop_oneorder where id=?";
        DbOpr.excute(sql,id);
    }


}
