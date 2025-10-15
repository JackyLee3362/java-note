package edu.note.spring.bean.anno;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.note.spring.bean.anno.config.SpringConfigPropertySource;

/**
 * @author jackylee
 * @date 2025-10-05 12:36
 */
public class ApplicationContextTest {

    @Test
    @DisplayName("测试 context 属性注入")
    void testPropertySource() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfigPropertySource.class);
        HelloDao dao = ctx.getBean(HelloDao.class);
        dao.save("foo");
        HelloService service = ctx.getBean(HelloService.class);
        service.hello("bar");
    }

}
