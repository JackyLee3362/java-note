package com.example.aop;

// import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//切面类
//@Aspect
@Component
public class MyAspect7 {

    //匹配DeptServiceImpl中的 list() 和 delete(Integer id)方法
    //@Pointcut("execution(* com.example.service.DeptService.list()) || execution(* com.example.service.DeptService.delete(java.lang.Integer))")
    @Pointcut("@annotation(com.example.anno.MyLog)")
    private void pt(){}

    @Before("pt()")
    public void before(){
        System.out.println("MyAspect7 ... before ...");
    }

}
