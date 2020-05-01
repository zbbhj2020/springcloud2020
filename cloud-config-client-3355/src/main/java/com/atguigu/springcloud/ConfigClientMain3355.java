package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: zbb
 * @create: 2020/4/23 19:34
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClientMain3355
{
    public static void main(String[] args)
    {
        SpringApplication.run(ConfigClientMain3355.class, args);
    }
}
