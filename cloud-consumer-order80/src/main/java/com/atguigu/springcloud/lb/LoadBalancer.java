package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author: zbb
 * @create: 2020/4/21 20:38
 */
public interface LoadBalancer
{
    // instances()方法: 得到 保存ServiceInstance服务实例的List集合；
    public ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
