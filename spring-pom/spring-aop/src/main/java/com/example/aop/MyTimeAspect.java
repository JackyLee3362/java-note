package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
// @Aspect
public class MyTimeAspect {
    @Pointcut("execution(* com.example.service.*.*(..))")
    private void pt() {

    }

    @Around("pt()")
    public Object Timer(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around开始计时");
        long start = System.currentTimeMillis();
        Object o = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println(proceedingJoinPoint.getSignature()+"程序执行的时间是"+(Object) (end - start)+"毫秒" );
        return o;
    }

    @Before("execution(* com.example.service.*.*(..))")
    public void Advice() {
        System.out.println("被调用了");
    }
}
