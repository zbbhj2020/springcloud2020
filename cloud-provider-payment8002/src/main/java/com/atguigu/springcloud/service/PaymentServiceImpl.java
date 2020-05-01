package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

// 这是一个Service服务类
@Service
public class PaymentServiceImpl implements PaymentService
{
    // @Resource和Autowired都是依赖注入
    @Resource
    private PaymentDao paymentDao;

    // 增
    public int create(Payment payment)
    {
        int i = paymentDao.create(payment);
        return i;
    }
    // 查
    public Payment getPaymentById(Long id)
    {
        Payment payment = paymentDao.getPaymentById(id);
        return payment;
    }
}
