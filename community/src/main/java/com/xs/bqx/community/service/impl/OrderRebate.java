package com.xs.bqx.community.service.impl;


import com.xs.bqx.community.pojo.OrderInfo;
import com.xs.bqx.community.service.OrderStrategyService;
import org.springframework.stereotype.Component;

@Component("Rebate")
public class OrderRebate implements OrderStrategyService {
    @Override
    public String preCreateOrder(OrderInfo orderInfo) {

        System.out.println("***处理国内特殊回扣预下单的相关业务***");
        return orderInfo.getPlatFormType()+"-特殊回扣预下单";


    }
}
