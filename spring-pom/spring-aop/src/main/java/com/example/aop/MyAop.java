package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAop {
    @Pointcut("execution(* com.aopdemo.*.*(..)) && @annotation(com.aopdemo.anno.MyAnno)")
    public void ptAnd() {
    }
    
    @Pointcut("execution(* com.aopdemo.*.*(..)) || @annotation(com.aopdemo.anno.MyAnno)")
    public void ptOr(){
    }

    @Pointcut("execution(* com.aopdemo.web.*.*(..))")
    public void pt() {
    }

    @Pointcut("@annotation(com.aopdemo.anno.MyAnno)")
    public void anno() {
    }

    @Before("anno()")
    public void before(JoinPoint joinPoint) {
        System.out.println("anno: 调用前...");
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getTarget());
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("webPt: 调用前...");
        // 调用目标对象的原始方法执行
        Object result = point.proceed();
        System.out.println("webPt: 调用后...");
        return result;
    }

    @After("pt")
    public void after() {
        System.out.println("pt(): 调用后...");
    }

    @AfterReturning("pt()")
    public void afterReturning() {
        System.out.println("pt(): 调用返回后...");
    }

    @AfterThrowing("pt()")
    public void afterThrowing() {
        System.out.println("pt(): 调用发生错误时...");
    }

}
