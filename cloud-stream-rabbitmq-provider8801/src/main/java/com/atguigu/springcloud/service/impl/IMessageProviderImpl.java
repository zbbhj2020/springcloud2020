package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author: zbb
 * @create: 2020/4/24 9:13
 */
@EnableBinding(Source.class) // 定义消息的推送管道   开启资源绑定
@Slf4j
public class IMessageProviderImpl implements IMessageProvider
{
    @Resource
    private MessageChannel output; // 消息发送管道

    @Override
    public String send()
    {
        // 随机序列号生成器
        String serial = UUID.randomUUID().toString();
        // 发送序列号
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("****serial：" + serial);
        return null;
    }
}
