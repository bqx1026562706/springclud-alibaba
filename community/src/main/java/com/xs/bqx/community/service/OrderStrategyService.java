package com.xs.bqx.community.service;

import com.xs.bqx.community.pojo.OrderInfo;
import org.springframework.stereotype.Service;

/**
 * 策略 实现接口
 */
@Service
public interface OrderStrategyService {

    //预下单
    String  preCreateOrder(OrderInfo orderInfo);


}
