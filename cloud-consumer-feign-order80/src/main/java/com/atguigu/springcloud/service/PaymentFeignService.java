package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import feign.Param;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: zbb
 * @create: 2020/4/22 9:46
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") // 指定微服务调用目标
public interface PaymentFeignService
{
    /**
     * @PathVariable 映射 URL 绑定的占位符
     * 带占位符的 URL 是 Spring3.0 新增的功能，该功能在SpringMVC 向 REST 目标挺进发展过程中具有里程碑的意义
     * 通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中：URL 中的 {xxx} 占位符可以
     * 通过@PathVariable(“xxx“) 绑定到操作方法的入参中。
     *
     * @param id
     * @return
     */
    // 查 泛型类，封装的是Payment对象
    // @PathVariable可以用来映射URL中的占位符到目标方法的参数中
    // 指定微服务调用地址
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();

}
