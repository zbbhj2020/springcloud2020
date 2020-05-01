package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: zbb
 * @create: 2020/4/24 9:09
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamMQMain8801
{
    public static void main(String[] args)
    {
        SpringApplication.run(StreamMQMain8801.class, args);
    }
}
