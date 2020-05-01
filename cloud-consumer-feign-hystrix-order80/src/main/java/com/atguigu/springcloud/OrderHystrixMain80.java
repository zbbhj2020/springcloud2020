package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: zbb
 * @create: 2020/4/22 14:27
 */
@SpringBootApplication
@EnableFeignClients  // 启动Feign客户端
@EnableHystrix  // 启动Hystrix
public class OrderHystrixMain80
{
    public static void main(String[] args)
    {
        SpringApplication.run(OrderHystrixMain80.class, args);
    }
}
