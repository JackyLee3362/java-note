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
public class Bean02_CreateBeanTest {

    @Test
    @DisplayName("测试获取 bean 的三种方式")
    void testBean_Basic() {
        // given:
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean/Bean-基础.xml");

        // expect: by name
        HelloDao dao1 = (HelloDao) ctx.getBean("helloDao");
        assertTrue(dao1.save("Foo"));

        // and: by type
        HelloDao dao2 = ctx.getBean(HelloDao.class);
        assertEquals(dao1, dao2);

        // and by name 和 type
        HelloDao dao3 = ctx.getBean("helloDao", HelloDao.class);
        assertEquals(dao1, dao3);

    }

    /**
     * 不推荐使用静态工厂实例化 Bean, 主要是兼容以前老方法, 了解即可
     */
    @Test
    @DisplayName("静态工厂创建 Bean")
    void testBean_StaticFactory() {
        // given:
        ApplicationContext context = new ClassPathXmlApplicationContext("bean/Bean-静态工厂.xml");

        // when:
        HelloDao dao = context.getBean(HelloDao.class);

        // then:
        assertTrue(dao.save("Foo"));
    }

    @Test
    @DisplayName("实例工厂创建 Bean")
    void testBean_InstanceFactory() {
        // given:
        ApplicationContext context = new ClassPathXmlApplicationContext("bean/Bean-实例工厂.xml");

        // when:
        HelloDao dao = context.getBean(HelloDao.class);

        // then:
        assertTrue(dao.save("Foo"));
    }

    /**
     * 该方法主要用作整合第三方类
     * 通过实现 FactoryBean 接口，来创建复杂对象
     */
    @Test
    @DisplayName("通过实现 FactoryBean 接口创建 Bean")
    void testBean_FactoryBeanImpl() {
        // given:
        ApplicationContext context = new ClassPathXmlApplicationContext("bean/Bean-FactoryBean接口.xml");

        // when:
        HelloDao dao = context.getBean(HelloDao.class);

        // then:
        assertTrue(dao.save("Foo"));
    }

    @Test
    @DisplayName("测试 Bean 别名")
    void testBean_Alias() {
        // given:
        ApplicationContext context = new ClassPathXmlApplicationContext("bean/Bean-别名.xml");

        // when:
        HelloService service = (HelloService) context.getBean("service");

        // then:
        assertEquals("Hello, Foo", service.hello("Foo"));
    }

    @Test
    @DisplayName("测试 Bean 作用域")
    void testBean_Scope() {
        // given:
        ApplicationContext context = new ClassPathXmlApplicationContext("bean/Bean-作用域.xml");

        // when:
        HelloDao dao1 = context.getBean(HelloDao.class);

        // and:
        HelloDao dao2 = context.getBean(HelloDao.class);

        // then: singleton scope
        assertEquals(dao1, dao2);

        // when:
        HelloService service1 = context.getBean(HelloService.class);

        // and:
        HelloService service2 = context.getBean(HelloService.class);

        // then: prototype scope
        assertNotEquals(service1, service2);
    }

}
