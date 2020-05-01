package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: zbb
 * @create: 2020/4/2020:36
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient // 启动服务注册与发现
public class PaymentMain8002
{
    public static void main(String[] args)
    {
        SpringApplication.run(PaymentMain8002.class, args);
    }
}
