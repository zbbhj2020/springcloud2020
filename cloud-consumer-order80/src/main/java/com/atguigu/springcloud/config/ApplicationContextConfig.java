package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zbb
 * @create: 2020/4/2014:10
 */
// 这是一个配置类
@Configuration
public class ApplicationContextConfig
{
    /**
     * @Bean 注入容器中，作用：如spring 创建容器[bean工厂]
     *      ApplicationContext.xml配置文件中 <bean id="RestTemplate" class="全类名"/>
     * @return RestTemplate
     */
    // Bean对象注入容器中让其他类引用
    @Bean
//    @LoadBalanced // 开启负载均衡 使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
