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
public class MyAdviceAroundVoid {

    @Pointcut("execution(void edu.note.spring.aop.BookDao.update())")
    private void pt() {
    }

    // 对有返回值的函数会报错
    @Around("pt()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice ...");
        // 表示对原始操作的调用
        pjp.proceed();
        System.out.println("around after advice ...");
    }

}
