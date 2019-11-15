package com.woniu.service;

import com.woniu.dao.OneOrderDao;
import com.woniu.dao.OrderDao;
import com.woniu.dao.ProductDao;
import com.woniu.entity.OneOrder;
import com.woniu.entity.Order;
import com.woniu.entity.Product;
import com.woniu.entity.User;
import com.woniu.jdbc.DbOpr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/*      state=1       购物车状态
        state=2       订单状态      */
@Service
public class ShoppingCarService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OneOrderDao oneOrderDao;

    //根据用户id创建一个购物车
    public void createOrderByUid(int uId){
       orderDao.createOrderByUid(uId);
    }

    //点击购买加入购物车
    public boolean  buySomething(Integer productid, Integer num, User user){
        //System.out.println("num:"+num);
        //System.out.println("productId:"+productid);
        int shopcarId=-1;
        if(num>0&&productDao.showIdventoryByNum(productid)>=num){
            shopcarId=orderDao.getShoppingCarIdByUid(user.getId());
            if(shopcarId==-1){
                orderDao.createOrderByUid(user.getId());
                shopcarId=orderDao.getShoppingCarIdByUid(user.getId());
            }
            //System.out.println(shopcarId);
            if(oneOrderDao.ifProductInShoppingCar(shopcarId,productid)){
                int oneOrderId=oneOrderDao.getIdByPidAndOid(productid,shopcarId);
                if (oneOrderId==-1){
                    return false;
                }
                Integer previousNum=oneOrderDao.getBuyNumById(oneOrderId);
                num+=previousNum;
                oneOrderDao.changeNumInOneOrder(oneOrderId,num);
            }else{
                oneOrderDao.addOneOrderBypIdAndNumAndOrderId(productid,num,shopcarId);
            }
            return true;
        }
        return false;
    }

    //显示用户购物车中所有的商品购买信息
    public List<OneOrder> showAllInShoppingcar(int uId){
        int oId=orderDao.getShoppingCarIdByUid(uId);
        List<OneOrder> list=oneOrderDao.getAllOneOrderByOrderId(oId);
        return list;
    }

    //根据id删除OneOrder
    public void deleteOneOrderById(int id){
       oneOrderDao.deleteOneOrderById(id);
    }

    //接收离开页面发送的数据,修改购物车商品数量
    public void changeBuyNumWhenLeave(List<OneOrder> list){
        for (OneOrder oneOrder : list) {
            oneOrderDao.changeNumInOneOrder(oneOrder.getId(),oneOrder.getBuyNum());
        }
    }

    //根据buyNum和price计算价格
    public double countTotalByOrderId(int oId){
        List<OneOrder> list=oneOrderDao.getAllOneOrderByOrderId(oId);
        //System.out.println("list:"+list.size());
        double sum=0;
        for (OneOrder oneOrder : list) {
            sum+=oneOrder.getBuyNum()*oneOrder.getPrice();
        }
        //System.out.println(sum);
        if(sum>0){
            return sum;
        }
        return -1;
    }
}
