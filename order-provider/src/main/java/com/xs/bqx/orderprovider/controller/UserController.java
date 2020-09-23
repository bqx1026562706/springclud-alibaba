package com.xs.bqx.orderprovider.controller;

import com.xs.bqx.orderprovider.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class UserController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/selectWxUserInfo")
    public List<Map> findById() {
        return orderService.selectWxUserInfo();
    }


}
