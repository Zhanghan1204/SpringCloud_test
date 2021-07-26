package com.sc.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/test01")
public class Test01 {

    @RequestMapping("/checkStr/{parent}/{child}")
    public String checkStr(@PathVariable("parent") String parent,@PathVariable("child") String child){
        //窗口的左边界
        int l = 0;
        //窗口的右边界
        int r = 0;
        //记录窗口中符合字符的个数
        int valid = 0;
        //记录最小覆盖字符串的起始索引及长度
        int start = 0,len = Integer.MAX_VALUE;
        System.out.println("len:"+len);
        //窗口中符合的字符
        HashMap<Character,Integer> window = new HashMap<>();
        //需要的字符
        HashMap<Character,Integer> needs = new HashMap<>();
        //记录child中字符出现的次数
        for (char c :child.toCharArray()){
            needs.put(c,needs.getOrDefault(c,0)+1);
        }
        System.out.println("needs:"+needs);
        while (r < parent.length()){
            //扩大窗口
            //根据下标获取parent对应的字符
            char c = parent.charAt(r);
            r++;

            //判断needs中是否包含字符
            if(needs.containsKey(c)){
                //getOrDefault:当Map集合中有这个key时，就使用这个key值，如果没有就使用默认值defaultValue
                //若当前字符满足条件,则更新windows中字符的出现次数
                window.put(c,window.getOrDefault(c,0)+1);
                //若遇到存在的字符,则窗口中的字符的总次数+1
                if(window.get(c).equals(needs.get(c))){
                    valid++;
                }
            }
            System.out.println("window:"+window);
            //满足条件(出现了需要的全部字符),则缩小窗口
            while (valid == needs.size()){
                System.out.println("valid:"+valid);
                System.out.println("进入第二个while时的r:"+r);
                System.out.println("进入第二个while时的l:"+l);
                if(r - l <len){
                    start = l;
                    len = r-l;
                }
                //开始缩小窗口
                char lchar = parent.charAt(l);
                l++;
                //更新窗口内的值
                if(needs.containsKey(lchar)){
                    //若左边的字符是needs中的,则窗口的window中字符出现次数-1,因为要移除左边的字符
                    if(needs.get(lchar).equals(window.get(lchar))){
                        valid--;
                    }
                    //移除窗口中window的字符出现次数
                    window.put(lchar,window.getOrDefault(lchar,0)-1);
                }

            }
        }
        //len判断是否存在,不存在则返回空,存在则截取parent
        String sub = len ==Integer.MAX_VALUE ?"":parent.substring(start,start+len);
        return sub;

    }

    public static void main(String[] args) {
        String parent ="AD0BECODEBANC";
        String child = "ABC";
        Test01 t = new Test01();
        System.out.println(t.checkStr(parent,child));


    }


}
