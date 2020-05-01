package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: zbb
 * @create: 2020/4/219:56
 */
@SpringBootApplication
@EnableDiscoveryClient  // 开启服务注册于发现
public class PaymentMain8004
{
    public static void main(String[] args)
    {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}
