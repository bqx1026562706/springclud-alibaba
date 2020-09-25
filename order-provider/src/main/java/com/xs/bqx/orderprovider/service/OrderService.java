package com.xs.bqx.orderprovider.service;

import com.xs.bqx.orderprovider.pojo.RabbitMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface OrderService {

    List<Map> selectWxUserInfo();



    void addmess(RabbitMessage re);
}
