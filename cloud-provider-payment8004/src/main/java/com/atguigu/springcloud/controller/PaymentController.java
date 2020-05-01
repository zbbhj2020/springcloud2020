package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author: zbb
 * @create: 2020/4/2110:00
 */
@RestController
@Slf4j
public class PaymentController
{
    // 该注解获取自身配置信息，具体什么内容，根据表达式中的内容指定
    // @Value("${server.port}"): 获取自身的端口号
    @Value("${server.port}")
    private String serverPort;

    /**
     * UUID.randomUUID().toString()是javaJDK提供的一个自动生成主键的方法。UUID(Universally Unique Identifier)全局唯一标识符,
     * 是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的，是由一个十六位的数字组成,表现出来的 形式。由以下几部分的组合：
     * 当前日期和时间(UUID的第一个部分与时间有关，如果你在生成一个UUID之后，过几秒又生成一个UUID，则第一个部分不 同，其余相同)，时钟序列，
     * 全局唯一的IEEE机器识别号（如果有网卡，从网卡获得，没有网卡以其他方式获得），UUID的唯一缺陷在于生成的结果串会比较长。
     * @return String
     */
    @RequestMapping(value = "/payment/zk")
    public String PaymentZk()
    {
        // return的一个String字符串信息。
        return "springcloud with zookeeper:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
