package com.woniu.dao;

import com.woniu.entity.Address;
import com.woniu.jdbc.DbOpr;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDao {

    //跟据用户显示所有的地址
    public List<Address> showAllAddressByUid(int uId){
        String sql="select * from shop_user_address where userId=?";
        List<Address> list= DbOpr.query(sql,Address.class,uId);
        if(list.size()>0){
            return list;
        }
        return null;
    }

    //创建新的地址
    public void createNewAddress(int uId,String name, String phone, String province, String city, String detail){
        String sql="insert into shop_user_address" +
                " (userId,name,phone,province,city,detailAddress,updateTime,createTime,`delete`)" +
                " values" +
                " (?,?,?,?,?,?,now(),now(),1)";
        DbOpr.excute(sql,uId,name,phone,province,city,detail);
    }
}
