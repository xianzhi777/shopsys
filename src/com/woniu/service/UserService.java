package com.woniu.service;

import com.woniu.entity.User;
import com.woniu.jdbc.DbOpr;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //根据account 和 password查询用户
    public User getUserByAccountAndPassword(String account, String password) {
        String sql = "select * from shop_user where account=? and password=?";
        User user = DbOpr.queryOne(sql, User.class, account, password);
        return user;
    }

    //查询用户名是否存在
    public boolean ifUserExist(String account){
        String sql="select * from shop_user where account=?";
        User user=DbOpr.queryOne(sql,User.class,account);
        if(user==null){
            return false;
        }
        return true;
    }

    //根据account 和password 创建用户
    public void createUserByAccountAndPassword(String account,String password){
        String sql="insert into shop_user" +
                " (account,password,updateTime,createTime,`delete`)" +
                " values" +
                " (?,?,now(),now(),1)";
        DbOpr.excute(sql,account,password);
    }
}
