package edu.note.spring.aop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jackylee
 * @date 2025-10-05 16:04
 */
public class MyAdviceTest {
    @Test
    @DisplayName("测试 AOP")
    void testMyAdvice() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = ctx.getBean(BookDao.class);
        bookDao.update();
    }
    
}
