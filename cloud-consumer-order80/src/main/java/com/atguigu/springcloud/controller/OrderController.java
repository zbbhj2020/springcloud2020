package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author: zbb
 * @create: 2020/4/2014:05
 */
@RestController
@Slf4j
public class OrderController
{
//    public  static final String PAYMENT_URL = "http://localhost:8001";
    public  static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    // 自动装填
    @Resource
    private RestTemplate restTemplate;

    // 自定义负载均衡
    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment)
    {
        // PAYMENT_URL + "/payment/create" 组合成完整地址：http://localhost:8001/payment/create 让客户端访问此地址
        CommonResult commonResult = restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        return commonResult;
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id)
    {
        // PAYMENT_URL + "/payment/get/{id}" 组合成完整地址：http://localhost:8001//consumer/payment/get/{id} 让客户端访问此地址
        CommonResult commonResult = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        return commonResult;
    }

    @GetMapping(value = "/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id")Long id)
    {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful())
        {
            return entity.getBody();
        }
        else{
            return new CommonResult<>(444,"操作失败，未查询到信息" + id);
        }
    }

    @GetMapping(value = "/consumer/postForEntity/create")
    public CommonResult<Payment> create2(Payment payment)
    {
        ResponseEntity<CommonResult> postForEntity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if (postForEntity.getStatusCode().is2xxSuccessful())
        {
            return postForEntity.getBody();
        }
        else{
            return new CommonResult<>(444,"数据插入不成功！" + payment);
        }
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB()
    {
        // 得到CLOUD-PAYMENT-SERVICE 服务的实例集合。
        // List<ServiceInstance> getInstances(String serviceId);
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        // 如果实例集合为空或者实例的个数小于等于0
        if ( instances == null || instances.size() <= 0 )
        {
            // 则返回空
            return null;
        }
        // 如果不为空，则
        // 得到服务实例
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        // 得到服务实例中的uri
        URI uri = serviceInstance.getUri();
        // 将uri地址以String字符串类型返回。
        // http://localhost/consumer/payment/lb
        return restTemplate.getForObject(uri + "/payment/lb" , String.class);
    }

    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }

}
