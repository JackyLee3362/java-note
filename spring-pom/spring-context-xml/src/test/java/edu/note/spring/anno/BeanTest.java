package edu.note.spring.anno;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.note.spring.HelloDao;
import edu.note.spring.HelloService;
import edu.note.spring.config.SpringConfigComponentScan;
import edu.note.spring.config.SpringConfigLifeCycle;

/**
 * @author jackylee
 * @date 2025-10-05 15:28
 */
public class BeanTest {
    
    @Test
    @DisplayName("测试组件扫描")
    void testComponentScan() {
        // 获取 IOC 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-component-scan.xml");
        HelloDao dao = context.getBean(HelloDao.class);
        dao.save("foo");
        HelloService service = context.getBean(HelloService.class);
        service.hello("bar");
    }

    @Test
    @DisplayName("注解扫描")
    void testComponentScanByAnno() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigComponentScan.class);
        HelloDao dao = context.getBean(HelloDao.class);
        dao.save("foo");
        HelloService service = context.getBean(HelloService.class);
        service.hello("bar");
    }

    @Test
    @DisplayName("测试 bean 生命周期")
    void testLifeCycle() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfigLifeCycle.class);
        HelloDao dao = context.getBean(HelloDao.class);
        dao.save("foo");
        context.close();
    }
}
