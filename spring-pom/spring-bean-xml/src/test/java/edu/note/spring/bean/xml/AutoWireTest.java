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
public class AutoWireTest {

    @Test
    @DisplayName("测试自动装配 - byType")
    void testAutowireByType() {
        // 获取 IOC 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-autowire-by-type.xml");
        HelloService bean = ctx.getBean(HelloService.class);
        String res = bean.hello("Bar");
        assertEquals("Hello, Bar", res);
    }

    @Test
    @DisplayName("测试自动装配 - byName")
    void testAutowireByName() {
        // 获取 IOC 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-autowire-by-name.xml");
        HelloService bean = ctx.getBean(HelloService.class);
        String res = bean.hello("Bar");
        assertEquals("Hello, Bar", res);
    }

    @Test
    @DisplayName("测试自动装配 - 集合")
    void testAutowireCollection() {
        // 获取 IOC 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-autowire-collection.xml");
        HelloService res = ctx.getBean(HelloService.class);
        res.hello("Bar");
    }

}