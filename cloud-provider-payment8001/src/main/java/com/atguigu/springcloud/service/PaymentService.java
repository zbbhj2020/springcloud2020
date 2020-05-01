package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService
{
    // 增
    public int create(Payment payment);
    // 查
    public Payment getPaymentById(@Param("id") Long id);
}
