package com.atguigu.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: zbb
 * @create: 2020/4/2113:31
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderZKMain80
{
    public static void main(String[] args)
    {
        SpringApplication.run(OrderZKMain80.class, args);
    }
}
