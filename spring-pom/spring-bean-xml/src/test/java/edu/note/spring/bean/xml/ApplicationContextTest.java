package edu.note.spring.bean.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jackylee
 * @date 2025-09-30 14:03
 * @desc 获取 bean
 */
public class ApplicationContextTest {

    @Test
    @DisplayName("测试 context 创建方式")
    void testApplicationContext() {
        // 创建方式1: 类路径下 xml
        ApplicationContext ctx1 = new ClassPathXmlApplicationContext("bean-empty.xml");
        // 创建方式2: 文件系统路径 xml(不推荐)
        // ApplicationContext ctx2 = new FileSystemXmlApplicationContext("绝对路径.xml");
    }

}
