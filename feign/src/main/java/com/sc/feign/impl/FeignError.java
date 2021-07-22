package com.sc.feign.impl;

import com.sc.entity.Student;
import com.sc.feign.FeignProviderClient;
import org.springframework.stereotype.Component;

import java.util.Collection;

// @Component将FeignError 实例注⼊ IoC 容器中
// 实现类 FeignError，定义容错处理逻辑,即系统报错后的返回信息
// 在 FeignProviderClient 定义处通过 @FeignClient 的 fallback 属性设置映射,映射到该实现类FeignError
@Component
public class FeignError implements FeignProviderClient {

    @Override
    public Collection<Student> findAll() {
        return null;
    }

    @Override
    public String index() {
        return "服务器维护中.....";
    }
}
