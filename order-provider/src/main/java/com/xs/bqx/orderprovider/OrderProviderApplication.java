package com.xs.bqx.orderprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderProviderApplication.class, args);
    }

}
