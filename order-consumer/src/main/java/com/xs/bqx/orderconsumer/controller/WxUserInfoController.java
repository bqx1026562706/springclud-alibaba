package com.xs.bqx.orderconsumer.controller;

import com.xs.bqx.orderconsumer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wxUser")
public class WxUserInfoController {

    @Autowired
    private OrderService orderService;

    /**
     * 配置了 FeignClient  想不明白的是：服务的提供者 需要controller 么，若只想做一个单纯的服务者呢
     * @return
     */
    @GetMapping("/selectWxUserInfo")
    @ResponseBody
    public List<Map> selectWxUserInfo(){
        List<Map> userList =   orderService.selectWxUserInfo();
        return userList;
    }



}
