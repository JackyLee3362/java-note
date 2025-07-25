package edu.note.spring.aop.aop;

// import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//切面类
//@Aspect
@Component
public class MyAspect7 {

    //匹配DeptServiceImpl中的 list() 和 delete(Integer id)方法
    //@Pointcut("execution(* note.spring.aop.controller.service.DeptService.list()) || execution(* note.spring.aop.controller.service.DeptService.delete(java.lang.Integer))")
    @Pointcut("@annotation(edu.note.spring.aop.anno.MyAnno)")
    private void pt(){}

    @Before("pt()")
    public void before(){
        System.out.println("MyAspect7 ... before ...");
    }

}
