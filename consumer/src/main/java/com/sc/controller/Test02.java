package com.sc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test02")
public class Test02 {

    @Value("${hello.world}")
    private  String word;

    @RequestMapping("/index")
    public String index(){
        return this.word;
    }

}
