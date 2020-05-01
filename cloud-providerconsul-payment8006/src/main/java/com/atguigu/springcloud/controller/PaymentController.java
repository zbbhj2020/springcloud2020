package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author: zbb
 * @create: 2020/4/21 16:12
 */
@RestController
public class PaymentController
{
    @Value("${server.port}")
    public String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String PaymentConsul()
    {
        // return的一个String字符串信息。
        return "springcloud with consul:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
