package edu.note.spring.xml;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.note.spring.HelloService;

/**
 * @author jackylee
 * @date 2025-10-05 12:02
 */
public class ThirdPackageInjectTest {
    @Test
    @DisplayName("测试 Druid 连接池")
    void testDruid() {
        // 获取 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-druid.xml");
        HelloService bean = context.getBean(HelloService.class);
        String hello = bean.hello("Bar");
    }
    
    @Test
    @Disabled
    @DisplayName("测试 c3p0 连接池")
    void testC3p0() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-c3p0.xml");
        HelloService bean = context.getBean(HelloService.class);
        String hello = bean.hello("Bar");
    }
}
