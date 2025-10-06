package edu.note.spring.aop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.note.spring.aop.config.SpringConfigAfter;
import edu.note.spring.aop.config.SpringConfigAfterReturning;
import edu.note.spring.aop.config.SpringConfigAfterThrowing;
import edu.note.spring.aop.config.SpringConfigAroundObj;
import edu.note.spring.aop.config.SpringConfigAroundVoid;
import edu.note.spring.aop.config.SpringConfigBefore;

/**
 * @author jackylee
 * @date 2025-10-05 16:04
 */
public class MyAdviceTest {
    @Test
    @DisplayName("测试 AOP after")
    void testMyAdviceAfter() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfigAfter.class);
        BookDao bookDao = ctx.getBean(BookDao.class);
        bookDao.update();
    }

    // @Test
    // @DisplayName("测试 AOP before")
    // void testMyAdviceBefore() {
    //     ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfigBefore.class);
    //     BookDao bookDao = ctx.getBean(BookDao.class);
    //     bookDao.update();
    // }

    // @Test
    // @DisplayName("测试 AOP around void")
    // void testMyAdviceAroundVoid() {
    //     ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfigAroundVoid.class);
    //     BookDao bookDao = ctx.getBean(BookDao.class);
    //     bookDao.update();
    // }

    // @Test
    // @DisplayName("测试 AOP around obj")
    // void testMyAdviceAroundObj() {
    //     ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfigAroundObj.class);
    //     BookDao bookDao = ctx.getBean(BookDao.class);
    //     bookDao.update();
    // }

    // @Test
    // @DisplayName("测试 AOP after return")
    // void testMyAdviceAfterReturn() {
    //     ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfigAfterReturning.class);
    //     BookDao bookDao = ctx.getBean(BookDao.class);
    //     bookDao.update();
    // }

    // @Test
    // @DisplayName("测试 AOP after throw")
    // void testMyAdviceAfterThrow() {
    //     ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfigAfterThrowing.class);
    //     BookDao bookDao = ctx.getBean(BookDao.class);
    //     bookDao.update();
    // }
}
