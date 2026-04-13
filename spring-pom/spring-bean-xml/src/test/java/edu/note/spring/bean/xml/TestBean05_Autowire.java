package edu.note.spring.bean.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jackylee
 * @date 2025-10-05 11:48
 */
@SuppressWarnings("resource")
public class TestBean05_Autowire {

    @Test
    @DisplayName("测试自动装配 - byType")
    void testAutowireByType() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean/Bean-自动装配-byType.xml");
        HelloService bean = ctx.getBean(HelloService.class);
        String res = bean.hello("Bar");
        assertEquals("Hello, Bar", res);
    }

    @Test
    @DisplayName("测试自动装配 - byName")
    void testAutowireByName() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean/Bean-自动装配-byName.xml");
        HelloService bean = ctx.getBean(HelloService.class);
        String res = bean.hello("Bar");
        assertEquals("Hello, Bar", res);
    }

    @Test
    @DisplayName("测试自动装配 - 集合")
    void testAutowireCollection() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean/Bean-自动装配-集合.xml");
        HelloService res = ctx.getBean(HelloService.class);
        res.hello("Bar");
    }

}