package com.sc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/remote")
public class RemoteHandler {

    @Value("${hello.world}")
    private String port;

    @GetMapping("/index")
   public String index(){
       return "端口:"+this.port;
   }
}
