package edu.note.spring.aop.aop;

import org.aspectj.lang.annotation.After;
// import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
//@Aspect
public class AspectOrder5 {

    @Before("execution(* note.spring.aop.controller.service.impl.DeptServiceImpl.*(..))")
    public void before(){
        System.out.println("before ...5");
    }

    @After("execution(* note.spring.aop.controller.service.impl.DeptServiceImpl.*(..))")
    public void after(){
        System.out.println("after ...5");
    }

}
