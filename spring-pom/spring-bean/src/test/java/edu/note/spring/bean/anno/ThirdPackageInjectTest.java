package edu.note.spring.bean.anno;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.note.spring.bean.anno.config.SpringConfigImport;
import edu.note.spring.bean.anno.config.SpringConfigThirdPackage;
import edu.note.spring.bean.anno.config.SpringConfigThirdPkgWithRef;
import edu.note.spring.bean.anno.config.SpringConfigThirdPkgWithValue;


/**
 * @author jackylee
 * @date 2025-10-05 15:28
 */
public class ThirdPackageInjectTest {
    @Test
    @DisplayName("测试第三方包")
    void testThirdPackage() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfigThirdPackage.class);
        DataSource ds = ctx.getBean(DataSource.class);
        assertNotNull(ds);
    }

    @Test
    @DisplayName("测试第三方包导入 - 注入简单类型")
    void testThirdPackageWithValue() {
        // 使用配置文件
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfigThirdPkgWithValue.class);
        DataSource ds = ctx.getBean(DataSource.class);
        assertNotNull(ds);
    }

    @Test
    @DisplayName("测试第三方包导入 - 注入引用类型")
    void testThirdPackageWithRef() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfigThirdPkgWithRef.class);
        DataSource ds2 = ctx.getBean(DataSource.class);
        assertNotNull(ds2);
    }

    @Test
    @DisplayName("测试第三方包 - Import")
    void testImport() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfigImport.class);
        DataSource ds1 = ctx.getBean(DataSource.class);
        assertNotNull(ds1);
    }

}
