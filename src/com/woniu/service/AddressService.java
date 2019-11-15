package com.woniu.service;

import com.woniu.dao.AddressDao;
import com.woniu.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;

    public List<Address> showAllAddressByUid(int uid){
        return addressDao.showAllAddressByUid(uid);
    }

    public void createNewAddress(int uId,String name, String phone, String province, String city, String detail){
        addressDao.createNewAddress(uId,name,phone,province,city,detail);
    }

}
