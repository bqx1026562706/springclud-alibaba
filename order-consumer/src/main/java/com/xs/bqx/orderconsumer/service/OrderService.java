package com.xs.bqx.orderconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@FeignClient(name = "product")
public interface OrderService {

    @RequestMapping(value = "/product/selectWxUserInfo",method = RequestMethod.GET)
    List<Map> selectWxUserInfo();

}
