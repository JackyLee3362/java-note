package edu.note.spring.aop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author jackylee
 * @date 2025-10-05 15:58
 */

@Component
@Aspect
public class MyAdviceAfter {

    @Pointcut("execution(void edu.note.spring.aop.BookDao.update())")
    private void pt() {
    }

    @After("pt()")
    public void after() {
        System.out.println("after advice ...");
    }

}
