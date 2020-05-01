package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: zbb
 * @create: 2020/4/22 13:44
 */
@Service
public class PaymentService
{
    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id)
    {
        // Thread.currentThread().getName():获取线程池名称
        return "线程池：" + Thread.currentThread().getName() + "   paymentInfo_OK,id:   " + id + "\t" + "OK";
    }

    // @HystrixCommand：表示该方法出现异常时，将处理业务转移至指定方法
    // @HystrixProperty：表示执行线程隔离，隔离值为3000毫秒-3秒。
    // 该方法运行时即时出错也会被转移至替代方法运行。
    @HystrixCommand(fallbackMethod = "payment_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" , value = "1500")} )
    public String paymentInfo_TimeOut(Integer id)
    {
        int timeNum = 10;
        // 设置10秒延迟
        try { TimeUnit.SECONDS.sleep(timeNum); } catch (InterruptedException e) { e.printStackTrace(); }

        // Thread.currentThread().getName():获取线程池名称
        return "线程池：" + Thread.currentThread().getName() + "   paymentInfo_TimeOut,id:   " + id + "\t" + "耗时(秒)"+timeNum+"秒钟";
    }

    // 应急处理、兜底、转移调用。随便怎么说
    public String payment_TimeOutHandler(Integer id)
    {
        return "线程池：" + Thread.currentThread().getName() + "   payment_TimeOutHandler,id:   " + id + "\t" + "o(╥﹏╥)o";
    }

    // =========服务熔断============
    /**
     * fallbackMethod：指定转移调用应急方法paymentCircuitBreaker_fallback
     * commandProperties：指令属性
     * circuitBreaker.enabled：指定是否开启熔断器
     * circuitBreaker.requestVolumeThreshold ：请求量阀值[请求次数阀值]
     * circuitBreaker.sleepWindowInMilliseconds：指定事件窗口期，无法访问的空档时间
     * circuitBreaker.errorThresholdPercentage：指定跳闸失败率[误差阈值百分比]
     * 假定访问页面，当页面出现异常，且访问失败率达到60%时(超过6次失败)，开启熔断器，页面访问禁止，且熔断器处于半开状态，
     * 并启动设置的窗口时间；在窗口时间内，当访问数低于60%失败率时，则关闭熔断器，开启页面访问，如果访问失败率还是60%以上，则再次开启熔断器。
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    // 熔断器组件[方法]
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        // 设定访问id 如果小于0
        if(id < 0)
        {
            // 报运行异常，该异常在运行阶段出现，编译阶段不会出错
            throw new RuntimeException("******id 不能负数");
        }
        // 如果id不小于0，获取id的序列号的两种方式
//        String uuid = UUID.randomUUID().toString();
        String serialNumber = IdUtil.simpleUUID();
        // 返回线程名称和序列号信息。
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }

    // 兜底、应急处理、服务调用转移组件[方法]
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }
}
