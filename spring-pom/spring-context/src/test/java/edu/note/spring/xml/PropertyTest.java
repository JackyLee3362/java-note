package edu.note.spring.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.note.spring.datasource.HelloDataSource;

/**
 * @author jackylee
 * @date 2025-10-05 12:42
 */
public class PropertyTest {
    
    @Test
    @DisplayName("测试组件扫描")
    void testComponentScan() {
        // 获取 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-property.xml");
        HelloDataSource dataSource = context.getBean(HelloDataSource.class);
        Assertions.assertEquals("root", dataSource.getUsername());
    }
}
