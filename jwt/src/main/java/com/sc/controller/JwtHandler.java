package com.sc.controller;


import com.sc.util.JwtTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/jwt")
public class JwtHandler {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/setJwt/{username}/{role}")
    @ResponseBody
    public String setJwt(@PathVariable("username") String username,
                         @PathVariable("role") String role){
        JwtTest jwt = new JwtTest();
        return jwt.jwt(username,role,request);
    }

    @RequestMapping("/getjwt")
    @ResponseBody
    public String getJwt(){
        String token =  (String)request.getSession().getAttribute("jwtToken");
        System.out.println(token);
        JwtTest jwtTest = new JwtTest();
        return jwtTest.parse(request,token);
    }

}
