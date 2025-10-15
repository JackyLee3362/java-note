package edu.note.spring.bean.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jackylee
 * @date 2025-10-05 12:15
 */
public class BeanTest {

    @Test
    @DisplayName("测试 bean 基础")
    void testIOC01() {
        // 获取 IOC 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-ioc.xml");
        // 获取 bean 的三种方式
        // 方式 1
        HelloDao dao1 = (HelloDao) ctx.getBean("helloDao");
        // 方式 2
        HelloDao dao2 = ctx.getBean(HelloDao.class);
        // 方式 3
        HelloDao dao3 = ctx.getBean("helloDao", HelloDao.class);

        // 测试他们完全相同
        Assertions.assertEquals(dao1, dao2);
        Assertions.assertEquals(dao2, dao3);

        Boolean save = dao1.save("Foo");
        Assertions.assertTrue(save);
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

    @Test
    @DisplayName("测试构造器注入")
    void testConstrctorArg() {
        // 获取 IOC 容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-constructor-arg.xml");
        HelloService bean = context.getBean(HelloService.class);
        String hello = bean.hello("Bar");
        ;
        Assertions.assertEquals("Hello, Bar", hello);
    }
}
