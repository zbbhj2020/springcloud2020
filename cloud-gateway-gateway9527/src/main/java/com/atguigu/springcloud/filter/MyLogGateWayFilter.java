package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author: zbb
 * @create: 2020/4/23 15:16
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter , Ordered
{

    // ServerWebExchange:交换机  GatewayFilterChain：网关过滤连
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        log.info("**********进入 MyLogGateWayFilter 过滤器：" + new Date());

        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname == null)
        {
            log.info("******用户名为null，非法用户，o(╥﹏╥)o");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete(); // 返回设置完成的参数
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder()
    {
        log.info("**********进入 MyLogGateWayFilter 过滤器：" + new Date());
        return 0;
    }
}
