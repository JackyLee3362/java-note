package edu.note.spring.anno;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.note.spring.HelloDao;
import edu.note.spring.HelloService;
import edu.note.spring.config.SpringConfigPropertySource;

/**
 * @author jackylee
 * @date 2025-10-05 12:36
 */
public class ApplicationContextTest {

    @Test
    @DisplayName("测试 context 属性注入")
    void testPropertySource() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigPropertySource.class);
        HelloDao dao = context.getBean(HelloDao.class);
        dao.save("foo");
        HelloService service = context.getBean(HelloService.class);
        service.hello("bar");
    }

}
