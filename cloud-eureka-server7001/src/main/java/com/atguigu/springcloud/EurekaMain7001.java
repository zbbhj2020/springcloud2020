package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: zbb
 * @create: 2020/4/2017:39
 */
@SpringBootApplication // 这是springboot应用程序
@EnableEurekaServer    // 启动EurekaServer Eureka服务端
public class EurekaMain7001
{
    public static void main(String[] args)
    {
        SpringApplication.run(EurekaMain7001.class, args);
    }
}
