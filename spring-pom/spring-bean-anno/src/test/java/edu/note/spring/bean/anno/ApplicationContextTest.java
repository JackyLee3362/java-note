package edu.note.spring.bean.anno;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.note.spring.bean.anno.config.BasicConfig;

/**
 * @author jackylee
 * @date 2025-10-05 12:36
 */
@SuppressWarnings("resource")
public class ApplicationContextTest {

    @Test
    @DisplayName("测试 context 属性注入")
    void testPropertySource() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BasicConfig.class);
        HelloDao dao = ctx.getBean(HelloDao.class);
        HelloService service = ctx.getBean(HelloService.class);
        assertNotNull(dao);
        assertNotNull(service);
    }

}
