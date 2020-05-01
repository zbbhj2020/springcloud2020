package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zbb
 * @create: 2020/4/22 14:34
 */
@RestController
@Slf4j
// 全局fallback注解
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController
{
    @Resource
    private PaymentHystrixService paymentHystrixService;
    /**
     * 正常访问
     */
    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String infoOk = paymentHystrixService.paymentInfo_OK(id);
        log.info("结果：" + infoOk);
        return infoOk;
    }
    /**
     * 超时访问
     */
    @GetMapping(value = "/consumer/payment/hystrix/timeout2/{id}")
    public String paymentInfo_TimeOut2(@PathVariable("id") Integer id)
    {
        String timeOut = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("结果：" + timeOut);
        return timeOut;
    }

    // 给该方法1.5秒的响应时间，如果1.5秒内没有响应成功，或者方法即时异常，都将被转移指定的fallbackMethod 方法中去执行。
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})
    @HystrixCommand // 表示当前方法出现异常或超时将默认转移至全局fallback方法中。
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        int i = 10 / 0;
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id)
    {
        return "我是消费者80,对方支付系统繁忙请10秒种后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    // 下面是全局fallback方法
    public String payment_Global_FallbackMethod()
    {
        return "Global异常处理信息，请稍后再试(╯‵□′)╯︵┻━┻";
    }

}
