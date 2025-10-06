package edu.note.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author jackylee
 * @date 2025-10-05 15:58
 */

@Component
@Aspect
public class MyAdviceAroundObj {

    @Pointcut("execution(int edu.note.spring.aop.BookDao.save())")
    private void pt() {
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice ...");
        // 表示对原始操作的调用
        Object obj = pjp.proceed();
        System.out.println("around after advice ...");
        return obj;
    }

}
