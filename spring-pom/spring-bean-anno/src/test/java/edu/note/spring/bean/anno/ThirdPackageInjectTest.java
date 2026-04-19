package edu.note.spring.bean.anno;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.note.spring.bean.anno.config.ImportConfig;
import edu.note.spring.bean.anno.config.ThirdPackageConfig;
import edu.note.spring.bean.anno.config.ThirdPackageWithRefConfig;
import edu.note.spring.bean.anno.config.ThirdPackageWithValueConfig;


/**
 * @author jackylee
 * @date 2025-10-05 15:28
 */
@SuppressWarnings("resource")
public class ThirdPackageInjectTest {
    @Test
    @DisplayName("测试第三方包")
    void testThirdPackage() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ThirdPackageConfig.class);
        DataSource ds = ctx.getBean(DataSource.class);
        assertNotNull(ds);
    }

    @Test
    @DisplayName("测试第三方包导入 - 注入简单类型")
    void testThirdPackageWithValue() {
        // 使用配置文件
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ThirdPackageWithValueConfig.class);
        DataSource ds = ctx.getBean(DataSource.class);
        assertNotNull(ds);
    }

    @Test
    @DisplayName("测试第三方包导入 - 注入引用类型")
    void testThirdPackageWithRef() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ThirdPackageWithRefConfig.class);
        DataSource ds2 = ctx.getBean(DataSource.class);
        assertNotNull(ds2);
    }

    @Test
    @DisplayName("测试第三方包 - Import")
    void testImport() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ImportConfig.class);
        DataSource ds1 = ctx.getBean(DataSource.class);
        assertNotNull(ds1);
    }

}
