package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zbb
 * @create: 2020/4/23 13:56
 */
@Configuration
public class GateWayConfig
{
//    // RouteLocatorBuilder：路由建造器
//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder)
//    {
//        return builder.routes()
//                .route("circuitbreaker_route", r -> r.path("/consumingServiceEndpoint")
//                        .filters(f -> f.circuitBreaker(c -> c.name("myCircuitBreaker").fallbackUri("forward:/inCaseOfFailureUseThis"))
//                                .rewritePath("/consumingServiceEndpoint", "/backingServiceEndpoint")).uri("lb://backing-service:8088")
//                        .build();
//    }
        @Bean
        public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder)
        {
            // 获取所有路由对象
            RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
            // 构建路由路径
            routes.route("payment_atguigu_routh",
                    r -> r.path("/guonei")
                            .uri("http://news.baidu.com/guonei")).build();
            // 返回所构建的路由路径
            return routes.build();
        }

    @Bean
    public RouteLocator routes2(RouteLocatorBuilder routeLocatorBuilder)
    {
        // 获取所有路由对象
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        // 构建路由路径
        routes.route("payment_atguigu_routh2",
                r -> r.path("/game")
                        .uri("http://news.baidu.com/game")).build();
        // 返回所构建的路由路径
        return routes.build();
    }

}
