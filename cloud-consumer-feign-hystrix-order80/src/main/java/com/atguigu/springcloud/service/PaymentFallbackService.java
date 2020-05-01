package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: zbb
 * @create: 2020/4/22 16:13
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService
{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "Sorry：PaymentFallbackService fall back paymentInfo_OK, (╯‵□′)╯︵┻━┻";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "Sorry：PaymentFallbackService fall back paymentInfo_TimeOut, (╯‵□′)╯︵┻━┻";
    }
}
