package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author: zbb
 * @create: 2020/4/22 22:07
 */
@SpringBootApplication
@EnableHystrixDashboard //启用仪表板
public class HystrixDashboardMain9001
{
    public static void main(String[] args)
    {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }
}
