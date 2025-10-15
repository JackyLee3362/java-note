package edu.note.spring.bean.xml;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jackylee
 * @date 2025-10-05 12:02
 */
public class ThirdPackageInjectTest {
    @Test
    @DisplayName("测试 Druid 连接池")
    void testDruid() {
        // 获取 IOC 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-druid.xml");
        DataSource bean = ctx.getBean(DataSource.class);
        Assertions.assertNotNull(bean);
    }

    @Test
    @DisplayName("测试 c3p0 连接池")
    void testC3p0() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-c3p0.xml");
        DataSource bean = ctx.getBean(DataSource.class);
        Assertions.assertNotNull(bean);
    }
}
