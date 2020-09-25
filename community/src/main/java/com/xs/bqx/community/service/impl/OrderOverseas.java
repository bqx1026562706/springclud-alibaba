package com.xs.bqx.community.service.impl;


import com.xs.bqx.community.pojo.OrderInfo;
import com.xs.bqx.community.service.OrderStrategyService;
import org.springframework.stereotype.Component;

@Component("Overseas")
public class OrderOverseas implements OrderStrategyService {
    @Override
    public String preCreateOrder(OrderInfo orderInfo) {
        System.out.println("**处理海外预下单的相关业务**");
        return orderInfo.getPlatFormType() + "-海外预下单";
    }

}

