package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zbb
 * @create: 2020/4/21 19:47
 */
@Configuration
public class MySelfRule
{
    // 注入容器当中
    @Bean
    public IRule myRule()
    {
        return new RandomRule();// 定义为随机
    }
}
