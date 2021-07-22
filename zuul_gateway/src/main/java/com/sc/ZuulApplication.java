package com.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableZuulProxy    //包含了 @EnableZuulServer ，设置该类是⽹关的启动类。
@EnableAutoConfiguration   //自动装载,就不用再配置@springbootapplication注解了
// 可以帮助 Spring Boot 应⽤将所有符合条件的 @Configuration 配置加载到当前 Spring Boot 创建并使⽤的 IoC 容器中。
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }
}
