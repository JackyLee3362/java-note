package edu.note.spring.bean.anno;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.note.spring.bean.anno.config.SpringConfigComponentScan;
import edu.note.spring.bean.anno.config.SpringConfigLifeCycle;

/**
 * @author jackylee
 * @date 2025-10-05 15:28
 */
public class BeanTest {

    @Test
    @DisplayName("获取所有组件")
    void testComponentAll() {
        // 获取 IOC 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-component-scan.xml");
        for (String name : ctx.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }

    @Test
    @DisplayName("测试组件扫描")
    void testComponentScan() {
        // 获取 IOC 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-component-scan.xml");
        HelloDao dao = ctx.getBean(HelloDao.class);
        dao.save("foo");
        HelloService service = ctx.getBean(HelloService.class);
        service.hello("bar");
    }

    @Test
    @DisplayName("注解扫描")
    void testComponentScanByAnno() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfigComponentScan.class);
        HelloDao dao = ctx.getBean(HelloDao.class);
        dao.save("foo");
        HelloService service = ctx.getBean(HelloService.class);
        service.hello("bar");
    }

    @Test
    @DisplayName("测试 bean 生命周期")
    void testLifeCycle() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
                SpringConfigLifeCycle.class);
        HelloDao dao = ctx.getBean(HelloDao.class);
        dao.save("foo");
        ctx.close();
    }
}
