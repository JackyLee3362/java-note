package edu.note.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jackylee
 * @date 2025-09-30 14:03
 */
public class ApplicationContextTest {

    @Test
    @DisplayName("测试 IOC 容器")
    void testIOC01() {
        // 获取 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-ioc.xml");
        HelloDao dao = context.getBean(HelloDao.class);
        Boolean save = dao.save("Foo");
        Assertions.assertTrue(save);
    }

    @Test
    @DisplayName("测试 DI")
    void testDI01() {
        // 获取 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di.xml");
        HelloService service = context.getBean(HelloService.class);
        String hello = service.hello("Foo");
        Assertions.assertEquals("Hello, Foo", hello);
    }

    @Test
    @DisplayName("静态工厂实例化")
    void testStaticFactoryMethod() {
        // 该方法主要兼容以前老方法，了解即可
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-factory-method.xml");
        HelloDao dao = context.getBean(HelloDao.class);
        Assertions.assertTrue(dao.save("Foo"));
    }

    @Test
    @DisplayName("工厂实例化")
    void testFactoryBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-factory-bean.xml");
        HelloDao dao = context.getBean(HelloDao.class);
        Assertions.assertTrue(dao.save("Foo"));
    }

    @Test
    @DisplayName("工厂 Bean 实现类")
    void testFactoryBeanImpl() {
        // 该方法主要用作整合第三方类
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-factory-impl.xml");
        HelloDao dao = context.getBean(HelloDao.class);
        Assertions.assertTrue(dao.save("Foo"));
    }
}
