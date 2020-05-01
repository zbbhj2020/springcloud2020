package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zbb
 * @create: 2020/4/24 17:30
 */
@RestController
public class PaymentController
{

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String echo(@PathVariable("id") Integer id)
    {
        return "nacos registry, serverPort：" + serverPort + "\t id" + id;
    }

}
