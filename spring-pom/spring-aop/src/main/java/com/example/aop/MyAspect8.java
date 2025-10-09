package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//切面类
// @Aspect
@Component
public class MyAspect8 {

    @Pointcut("execution(* com.example.service.DeptService.*(..))")
    private void pt(){}

    @Before("pt()")
    public void before(JoinPoint joinPoint){
        System.out.println("MyAspect8 ... before ...");
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("MyAspect8 around before ...");
        
        //1. 获取 目标对象的类名 .
        String className = joinPoint.getTarget().getClass().getName();
        System.out.println("目标对象的类名:" + className);
        
        //2. 获取 目标方法的方法名 .
        String methodName = joinPoint.getSignature().getName();
        System.out.println("目标方法的方法名: "+methodName);
        
        //3. 获取 目标方法运行时传入的参数 .
        Object[] args = joinPoint.getArgs();
        System.out.println("目标方法运行时传入的参数: "+Arrays.toString(args));

        //4. 放行 目标方法执行 .
        Object result = joinPoint.proceed();

        //5. 获取 目标方法运行的返回值 .
        System.out.println("目标方法运行的返回值: "+result);

        System.out.println("MyAspect8 around after ...");
        return result;
    }
}
