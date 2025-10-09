package com.example.controller;

import org.springframework.stereotype.Service;

import com.example.anno.MyAnno;

@Service
public class ControllerA {
    public String hello() {
        return "controller, hello";
    }

    @MyAnno
    public String world() {
        return "controller, world";
    }
}
