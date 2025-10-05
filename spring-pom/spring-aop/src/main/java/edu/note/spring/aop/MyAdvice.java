package edu.note.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author jackylee
 * @date 2025-10-05 15:58
 */

@Component
@Aspect
public class MyAdvice {

    @Pointcut("execution(void edu.note.spring.aop.BookDao.update())")
    private void pt() {
    }

    @Before("pt()")
    public void method() {
        System.out.println("before:" + System.currentTimeMillis());
    }
}
