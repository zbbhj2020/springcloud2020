package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;


// 这是一个RestController控制类,

/**
 * 1) 如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，或者html，
 *      配置的视图解析器 InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
 * 2) 如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
 *      如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
 * 3) 如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
 */
@RestController
// Slf4j日志注解，查看控制层运行流程信息
@Slf4j
public class PaymentController
{
    // @Resource和Autowired都是依赖注入
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    // 服务发现
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);

        if (result > 0)
        {
            return new CommonResult(200,"插入数据库成功!"+serverPort,result);
        } else {
            return new CommonResult(444,"插入数据库失败!",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id)
    {
        Payment payment = paymentService.getPaymentById(id);

        log.info("*****插入结果：" + payment + "O(∩_∩)O哈哈~");

        if (payment != null)
        {
            return new CommonResult(200,"尊敬的用户，数据库查询成功!"+serverPort,payment);
        } else {
            return new CommonResult(444,"数据库没有对应记录!，查询ID:" + id,null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String service : services)
        {
            // 获得基础服务列表清单
            log.info("****service:" + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances)
        {
            log.info(instance.getServiceId()+ "\t" +  instance.getHost() +"\t"+ instance.getPort()
                    + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout()
    {
        try
        {
            TimeUnit.SECONDS.sleep(3);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}
