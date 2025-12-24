package edu.note.spring.bean.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jackylee
 * @date 2025-10-05 12:15
 */
@SuppressWarnings("resource")
public class IT02_BeanCreationTest {

    @Test
    @DisplayName("测试 bean 基础")
    void test2_1_IOC() {
        // given: 获取 IOC 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("2.1-bean-ioc.xml");

        // when: 获取 bean 的三种方式
        // 方式 1: by name
        HelloDao dao1 = (HelloDao) ctx.getBean("helloDao");
        // 方式 2: by type
        HelloDao dao2 = ctx.getBean(HelloDao.class);
        // 方式 3: by name 和 type
        HelloDao dao3 = ctx.getBean("helloDao", HelloDao.class);

        // then: 断言
        assertTrue(dao1.save("Foo"));
        assertEquals(dao1, dao2);
        assertEquals(dao2, dao3);
    }

    @Test
    @DisplayName("静态工厂实例化")
    void test2_2_StaticFactoryMethod() {
        // 该方法主要兼容以前老方法，了解即可
        ApplicationContext context = new ClassPathXmlApplicationContext("2.2-bean-static-factory.xml");
        HelloDao dao = context.getBean(HelloDao.class);
        assertTrue(dao.save("Foo"));
    }

    @Test
    @DisplayName("工厂实例化")
    void test02_03_FactoryBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("2.3-bean-factory-bean.xml");
        HelloDao dao = context.getBean(HelloDao.class);
        assertTrue(dao.save("Foo"));
    }

    @Test
    @DisplayName("实现 FactoryBean 接口创建 Bean")
    void test02_04_FactoryBeanImpl() {
        // 该方法主要用作整合第三方类
        // 通过实现 FactoryBean 接口，来创建复杂对象
        ApplicationContext context = new ClassPathXmlApplicationContext("2.4-bean-factory-impl.xml");
        HelloDao dao = context.getBean(HelloDao.class);
        assertTrue(dao.save("Foo"));
    }


    @Test
    @DisplayName("测试别名")
    void testBeanAlias() {
        // 获取 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("2.5-bean-alias.xml");
        HelloService service = (HelloService) context.getBean("service");
        String hello = service.hello("Foo");
        assertEquals("Hello, Foo", hello);
    }

    @Test
    @DisplayName("测试 Scope")
    void testBeanScope() {
        // 获取 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("2.6-bean-scope.xml");
        HelloDao dao1 = context.getBean(HelloDao.class);
        HelloDao dao2 = context.getBean(HelloDao.class);
        assertEquals(dao1, dao2);

        HelloService service1 = context.getBean(HelloService.class);
        HelloService service2 = context.getBean(HelloService.class);
        assertNotEquals(service1, service2);
    }

}
