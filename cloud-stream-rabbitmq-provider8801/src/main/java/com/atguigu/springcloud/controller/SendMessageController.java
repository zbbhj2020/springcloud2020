package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zbb
 * @create: 2020/4/24 9:26
 */
@RestController
public class SendMessageController
{
    @Resource
    private IMessageProvider iMessageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage()
    {
        String send = iMessageProvider.send();
        return send;
    }
}
