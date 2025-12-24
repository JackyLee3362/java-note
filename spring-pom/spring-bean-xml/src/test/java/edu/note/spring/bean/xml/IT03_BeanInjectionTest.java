package edu.note.spring.bean.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jackylee
 * @date 2025-12-24 13:24
 */
@SuppressWarnings("resource")
public class IT03_BeanInjectionTest {

    @Test
    @DisplayName("测试 DI")
    void testDI01() {
        // 获取 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("3.1-bean-di.xml");
        HelloService service = context.getBean(HelloService.class);
        String hello = service.hello("Foo");
        assertEquals("Hello, Foo", hello);
    }

    @Test
    @DisplayName("测试构造器注入")
    void testConstrctorArg() {
        // 获取 IOC 容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("3.2-bean-constructor-arg.xml");
        HelloService bean = context.getBean(HelloService.class);
        String hello = bean.hello("Bar");
        assertEquals("Hello, Bar", hello);
    }
}
