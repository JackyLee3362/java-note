package edu.note.spring.bean.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jackylee
 * @date 2025-10-05 12:42
 */
@SuppressWarnings("resource")
public class TestBean06_Property {

    @Test
    @DisplayName("测试组件扫描")
    void testComponentScan() {
        // 获取 IOC 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean/Bean-属性注入.xml");
        HelloDataSource dataSource = ctx.getBean(HelloDataSource.class);
        assertEquals("root", dataSource.getUsername());
    }
}
