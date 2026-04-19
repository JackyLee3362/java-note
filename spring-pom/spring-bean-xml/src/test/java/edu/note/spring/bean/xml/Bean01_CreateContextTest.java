package edu.note.spring.bean.xml;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 测试 Context 创建
 * 
 * @author jackylee
 * @date 2025-09-30 14:03
 */
@SuppressWarnings("resource")
public class Bean01_CreateContextTest {

    @Test
    @DisplayName("测试类路径下 xml 创建 context")
    void test_ctx_01() {
        // 创建方式1: 类路径下 xml 创建 IOC 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean/Bean-创建.xml");
        assertNotNull(ctx);
    }

    @Test
    @DisplayName("测试文件系统路径 xml 创建 context")
    @Disabled("不推荐使用文件系统路径创建 IOC 容器")
    void test_ctx_02() {
        // 创建方式2: 文件系统路径 xml 创建 IOC 容器
        ApplicationContext ctx = new FileSystemXmlApplicationContext("绝对路径.xml");
        assertNotNull(ctx);
    }

    /**
     * 需要 @Component 注解配合
     */
    @Test
    @DisplayName("获取所有组件")
    void testComponentAll() {
        // 获取 IOC 容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean/Bean-组件扫描.xml");
        for (String name : ctx.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        HelloDao dao = ctx.getBean(HelloDao.class);
        dao.save("foo");
        // HelloService service = ctx.getBean(HelloService.class);
        // service.hello("bar");
    }

}
