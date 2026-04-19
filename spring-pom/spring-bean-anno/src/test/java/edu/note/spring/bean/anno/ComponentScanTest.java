package edu.note.spring.bean.anno;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import edu.note.spring.bean.anno.config.ComponentScanConfig;
import edu.note.spring.bean.anno.config.ComponentScanFilterConfig;
import edu.note.spring.bean.anno.config.LifeCycleConfig;

/**
 * @author jackylee
 * @date 2025-10-05 15:28
 */
@SuppressWarnings("resource")
public class ComponentScanTest {

    @Test
    @DisplayName("注解扫描")
    void testComponentScanByAnno() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        HelloDao dao = ctx.getBean(HelloDao.class);
        dao.save("foo");
        HelloService service = ctx.getBean(HelloService.class);
        service.hello("bar");
    }

    @Test
    @DisplayName("测试 bean 生命周期")
    void testLifeCycle() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
                LifeCycleConfig.class);
        HelloDao dao = ctx.getBean(HelloDao.class);
        dao.save("foo");
        ctx.close();
    }

    @Test
    @DisplayName("测试排除部分 Bean 后无法获取")
    void testFilterComponent() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
                ComponentScanFilterConfig.class);
        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            ctx.getBean(Service.class);
        });

    }
}
