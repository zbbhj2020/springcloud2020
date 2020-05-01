package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zbb
 * @create: 2020/4/22 12:49
 */
@Configuration
public class FeignConfig
{
    /**
    * feignClient配置日志级别
    * @return
    */
    @Bean
    Logger.Level feignLoggerLevel()
    {
        // 请求和响应的头信息,请求和响应的正文及元数据
        return Logger.Level.FULL;
    }
}
