package edu.note.spring.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.note.spring.HelloService;

/**
 * @author jackylee
 * @date 2025-10-05 11:48
 */
public class AutoWireTest {

    @Test
    @DisplayName("测试自动装配 - byType")
    void testAutowireByType() {
        // 获取 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-autowire-by-type.xml");
        HelloService bean = context.getBean(HelloService.class);
        String hello = bean.hello("Bar");
        Assertions.assertEquals("Hello, Bar", hello);
    }

    @Test
    @DisplayName("测试自动装配 - byName")
    void testAutowireByName() {
        // 获取 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-autowire-by-name.xml");
        HelloService bean = context.getBean(HelloService.class);
        String hello = bean.hello("Bar");
        Assertions.assertEquals("Hello, Bar", hello);
    }

    @Test
    @DisplayName("测试自动装配 - 集合")
    void testAutowireCollection() {
        // 获取 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-autowire-collection.xml");
        HelloService bean = context.getBean(HelloService.class);
        bean.hello("Bar");
    }
    
    
}