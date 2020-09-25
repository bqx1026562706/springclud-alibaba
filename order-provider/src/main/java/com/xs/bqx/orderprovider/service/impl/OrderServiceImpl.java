package com.xs.bqx.orderprovider.service.impl;

import com.xs.bqx.orderprovider.dao.OderMapper;
import com.xs.bqx.orderprovider.pojo.RabbitMessage;
import com.xs.bqx.orderprovider.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OderMapper oderMapper;

    @Override
    public List<Map> selectWxUserInfo() {
        List<Map> list = oderMapper.selectWxUserInfo();
        return list;
    }

    @Override
    public void addmess(RabbitMessage re) {
        oderMapper.addmess(re);
    }





}
