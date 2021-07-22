package com.sc.feign;

import com.sc.entity.Student;
import com.sc.feign.impl.FeignError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

//访问服务提供者provider的方法
//该注解是表示访问注册中心中的哪个服务提供者,value对应注册中心中服务提供者的名字
//fallback配置服务熔断后的映射类
@FeignClient(value = "provider",fallback = FeignError.class)
public interface FeignProviderClient {

    @GetMapping("/student/findAll")
    public Collection<Student> findAll();

    @GetMapping("/student/index")
    public String index();
}
