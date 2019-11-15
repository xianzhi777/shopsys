package com.woniu.dao;

import com.woniu.entity.Order;
import com.woniu.jdbc.DbOpr;
import org.springframework.stereotype.Repository;
import java.util.List;


/*state=1购物车状态,每个user至多一个state=1
* state=2订单未支付状态
* state=3订单已支付状态
* */
@Repository
public class OrderDao {

    //根据用户id创建一个state=1的order
    public void createOrderByUid(int uid){
        String sql="insert into shop_order" +
                " (aliPayAccount,userId,addressId,state,updateTime,createTime,`delete`,total)" +
                " values" +
                " (?,?,0,1,now(),now(),1,0)";
        DbOpr.excute(sql,"",uid);
    }

    //根据用户uId和state=1获取购物车编号 不存在返回-1
    public int getShoppingCarIdByUid(int uId){
        String sql="select * from shop_order where userId=? and state=1";
        Order o=DbOpr.queryOne(sql, Order.class,uId);
        if(o==null){
            return -1;
        }
        return o.getId();
    }




    //生成订单,将state修改为2,插入addressId,total
    public void changeShoppingCarAsOrder(int id,int aId,double total){
        String sql="update shop_order set addressId=? , total=? , state=2 where id=?";
        DbOpr.excute(sql,aId,total,id);
    }

    //根据用户uId和state=2获取未支付订单 不存在返回-1
    public List<Order> getOrderIdIdByUid(int uId){
        String sql="select * from shop_order where userId=? and state=2";
        List<Order> list=DbOpr.query(sql, Order.class,uId);
        if(list.size()==0){
            return null;
        }
        return list;
    }

    //根据用户uId和state=2获取已支付订单 不存在返回-1
    public List<Order> gethaspaidOrderIdIdByUid(int uId){
        String sql="select * from shop_order where userId=? and state=3";
        List<Order> list=DbOpr.query(sql, Order.class,uId);
        if(list.size()==0){
            return null;
        }
        return list;
    }

    //支付成功,将state改为3
    public void payfin(int oid){
        String sql="update shop_order set state=3 where id=?";
        DbOpr.excute(sql,oid);
    }

    //根据id删除order同时删除oneorder
    public void deleteOrderAndOneOrderById(int id){
        String sqlOrder="delete from shop_order where id=?";
        String sqlOneOrder="delete from shop_oneorder where orderId=?";
        DbOpr.excute(sqlOrder,id);
        DbOpr.excute(sqlOneOrder,id);
    }
}
