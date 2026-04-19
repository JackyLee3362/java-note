package edu.note.spring.bean.anno;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.note.spring.bean.anno.config.ImportConfig;
import edu.note.spring.bean.anno.config.ImportSelectorConfig;

/**
 * 测试导入第三方 Bean
 *
 * @author jackylee
 * @date 2026-04-19 16:06
 */
public class AutowirdTest {

    @Test
    @DisplayName("测试 - 启动类加上 Import")
    void test_basic_01() {
        // todo
    }

    @Test
    @DisplayName("测试 -  配置类加上 Import")
    void testImport() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ImportConfig.class);
        DataSource ds1 = ctx.getBean(DataSource.class);
        assertNotNull(ds1);
    }

    @Test
    @DisplayName("测试 - 配置类加上 ImportSelector 实现类")
    void test_import_selector() {
        // given:
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ImportSelectorConfig.class);
        // when:
        HelloDao ds1 = ctx.getBean(HelloDao.class);
        // then:
        assertNotNull(ds1);
    }

    @Test
    @DisplayName("测试 - 第三方定义 EnableXxx")
    void test_basic_0() {
        // 一般来说，第三方提供 EnableXxx 注解，该注解封装类 @Import({...}) 可以导入

    }

}
