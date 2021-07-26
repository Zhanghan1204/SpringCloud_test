package com.sc.util;

import com.netflix.client.http.HttpRequest;
import com.netflix.ribbon.proxy.annotation.Http;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import sun.nio.cs.US_ASCII;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

public class JwtTest {



    //定义有效期
    private long sysTime = 24*60*60*1000l;

    //定义签名信息,签名的作用:通过签名做绑定,获取时通过签名获取
    private String signature = "admin";

    //对信息进行加密
    //单元测试
    @org.junit.Test
    public String jwt(String username, String role,HttpServletRequest request){
        //创建jwt对象,通过JwtBuilder创建jwt加密信息
        JwtBuilder jwtBuilder = Jwts.builder();

        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ","JWT")    //设置类型
                .setHeaderParam("alg","HS256")  //设置加密算法
                //payload 载荷
                .claim("username", username)  //通过claim添加用户信息
                .claim("role",role)
                .setSubject("admin-test")   //设置主题,随便定义
                .setExpiration(new Date(System.currentTimeMillis()+sysTime))    //设置有效时间,通过系统时间获取到期时间
                .setId(UUID.randomUUID().toString())    //设置随机的id
                //signature  签名
                .signWith(SignatureAlgorithm.HS256,signature)  //采用HS256加密方式加密,并设置签名
                .compact();  //将所有的信息拼接起来,这样就会得到一个字符串

        HttpSession session = request.getSession();
        session.setAttribute("jwtToken",jwtToken);
        System.out.println("jwt数据:"+session.getAttribute("jwtToken"));
        return jwtToken;
    }


    //对数据解密
    @org.junit.Test
    public String  parse(HttpServletRequest request,String token){

        //String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2MjczOTA1OTksImp0aSI6Ijc4MTA5NDE3LWMxMjMtNGUyMC04MTdjLTliNDdhOTg5NTQ5YSJ9.AfDU1LNBKPLnd6072hJuRiIomOeKL03Pe2XLIh44D4E";
        //创建JwtParser,通过JwtParser进行解密
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJwt =  jwtParser.setSigningKey(signature)  //通过签名获取
                .parseClaimsJws(token);
        Claims claims = claimsJwt.getBody();
        System.out.println(claims.get("username"));
        System.out.println(claims.get("role"));
        System.out.println("id:"+claims.getId());
        System.out.println("过期时间:"+claims.getExpiration());
        return null;
    }
}
