package edu.note.spring.bean.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.note.spring.bean.xml.HelloDataSource;

/**
 * @author jackylee
 * @date 2025-10-05 12:42
 */
public class PropertyTest {
    
    @Test
    @DisplayName("测试组件扫描")
    void testComponentScan() {
        // 获取 IOC 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-property.xml");
        HelloDataSource dataSource = ctx.getBean(HelloDataSource.class);
        Assertions.assertEquals("root", dataSource.getUsername());
    }
}
