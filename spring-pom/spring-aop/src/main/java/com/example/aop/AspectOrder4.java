package com.example.aop;

import org.aspectj.lang.annotation.After;
// import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
//@Aspect
public class AspectOrder4 {

    @Before("execution(* com.example.service.impl.DeptServiceImpl.*(..))")
    public void before(){
        System.out.println("before ...4");
    }

    @After("execution(* com.example.service.impl.DeptServiceImpl.*(..))")
    public void after(){
        System.out.println("after ...4");
    }

}
