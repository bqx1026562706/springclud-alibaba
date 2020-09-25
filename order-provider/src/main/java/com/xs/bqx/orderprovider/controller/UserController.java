package com.xs.bqx.orderprovider.controller;

import com.xs.bqx.orderprovider.pojo.RabbitMessage;
import com.xs.bqx.orderprovider.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class UserController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/selectWxUserInfo")
    public List<Map> findById() {
        return orderService.selectWxUserInfo();
    }


    /**
     * 将接受到的消息  存入到数据库 rabbit_cusmer
     * @param re
     */
    @RequestMapping("/addmess")
    public  void addmess(@RequestBody RabbitMessage re){
        orderService.addmess(re);
    }



}
