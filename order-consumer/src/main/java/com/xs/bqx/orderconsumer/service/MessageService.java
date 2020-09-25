package com.xs.bqx.orderconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
public interface MessageService {


    @PostMapping("/addmess")
    void addMessageToMySql(Map testMessage);
}
