package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zbb
 * @create: 2020/4/21 20:43
 *
 * 自定义负载均衡
 */
@Component // 表示能被扫描   自动以负载均衡组件
public class MyLB implements LoadBalancer
{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //
    public final int getAndIncrement()
    {
        int current;// 现在的
        int next;// 下一次
        do {
            // 得到原子数当前值
            current = this.atomicInteger.get();
            // 如果下一次的值等于当前值 且大于等于 2147483647 则返回数字0，如果小于则返回：当前值+1；
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("*****第几次访问，次数：next：" + next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        // 用下一个值(第几次访问/下一次访问) % 服务实例的数量 取余 作为索引值。
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
