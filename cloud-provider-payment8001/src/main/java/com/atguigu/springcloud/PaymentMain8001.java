package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *  SpirngBoot 主程序启动Main方法入口
 *
 */
@SpringBootApplication
@EnableEurekaClient  // 启动Eureka客户端\
@EnableDiscoveryClient // 启动服务注册与发现
public class PaymentMain8001
{
    public static void main(String[] args)
    {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
