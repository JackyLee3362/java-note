package edu.note.spring.bean.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jackylee
 * @date 2025-12-24 13:22
 */
@SuppressWarnings("resource")
public class IT04_BeanLifeCycleTest {

    @Test
    @DisplayName("测试 bean 生命周期")
    void testBeanLifeCycle() {
        // 获取 IOC 容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("4.1-bean-life-cycle.xml");
        context.getBean(HelloDao.class);
        // 容器关闭时 destory
        context.close();
    }

    @Test
    @DisplayName("测试 bean 生命周期")
    void testBeanLifeCycleByHook() {
        // 获取 IOC 容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("4.2-bean-life-cycle-hook.xml");
        context.getBean(WorldDao.class);
        context.registerShutdownHook();
    }

}
