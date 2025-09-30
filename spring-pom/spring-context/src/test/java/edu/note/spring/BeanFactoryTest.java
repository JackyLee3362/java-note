package edu.note.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jackylee
 * @date 2025-09-30 14:49
 */
public class BeanFactoryTest {


    @Test
    @DisplayName("测试别名")
    void testBeanAlias() {
        // 获取 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-alias.xml");
        HelloService service = (HelloService) context.getBean("service");
        String hello = service.hello("Foo");
        Assertions.assertEquals("Hello, Foo", hello);
    }

    @Test
    @DisplayName("测试 Scope")
    void testBeanScope() {
        // 获取 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-scope.xml");
        HelloDao dao1 = context.getBean(HelloDao.class);
        HelloDao dao2 = context.getBean(HelloDao.class);
        Assertions.assertEquals(dao1, dao2);

        HelloService service1 = context.getBean(HelloService.class);
        HelloService service2 = context.getBean(HelloService.class);
        Assertions.assertNotEquals(service1, service2);
    }

    @Test
    @DisplayName("测试 bean 生命周期")
    void testBeanLifeCycle() {
        // 获取 IOC 容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-life-cycle.xml");
        context.getBean(HelloDao.class);
        // 容器关闭时 destory
        context.close();
    }

    @Test
    @DisplayName("测试 bean 生命周期")
    void testBeanLifeCycleByHook() {
        // 获取 IOC 容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-life-cycle-hook.xml");
        context.getBean(WorldDao.class);
        context.registerShutdownHook();
    }
}
