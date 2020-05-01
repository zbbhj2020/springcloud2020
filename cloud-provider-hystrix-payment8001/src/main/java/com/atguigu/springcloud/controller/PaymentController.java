package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zbb
 * @create: 2020/4/22 13:51
 */
@RestController
@Slf4j
public class PaymentController
{
    @Resource
    public PaymentService paymentService;

    // 返回端口号
    @Value("${server.port}")
    public String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String infoOk = paymentService.paymentInfo_OK(id);
        log.info("***结果：" + infoOk);
        return infoOk;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        // 变量timeOut中就是service类中paymentInfo_TimeOut()方法的方法体内容
        String timeOut = paymentService.paymentInfo_TimeOut(id);
        log.info("***结果：" + timeOut);
        return timeOut;
    }

    //====服务熔断====
    // 指定熔断。处理方法
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("****result: "+result);
        return result;
    }


}
