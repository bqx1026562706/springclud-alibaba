package com.xs.bqx.community.service.impl;


import com.xs.bqx.community.pojo.OrderInfo;
import com.xs.bqx.community.service.OrderStrategyService;
import org.springframework.stereotype.Component;

/**
 * @Author : JCccc
 * @CreateTime : 2020/5/11
 * @Description :国内
 **/
@Component("Domestic")
public class OrderDomestic implements OrderStrategyService {

    @Override
    public String preCreateOrder(OrderInfo orderInfo) {

        System.out.println("*处理国内预下单的相关业务*");
        return orderInfo.getPlatFormType()+"-国内预下单";
    }
}