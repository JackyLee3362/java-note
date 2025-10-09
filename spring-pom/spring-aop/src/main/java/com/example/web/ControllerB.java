package com.example.web;

import org.springframework.stereotype.Service;

import com.example.anno.MyAnno;

@Service
public class ControllerB {
    public String hello() {
        return "web, hello";
    }

    @MyAnno
    public String world() {
        return "web, world";
    }
    // TODO: 测试 com.example.aop 的方法
}
