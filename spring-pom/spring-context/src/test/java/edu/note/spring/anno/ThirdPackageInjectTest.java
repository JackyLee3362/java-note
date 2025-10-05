package edu.note.spring.anno;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.note.spring.config.SpringConfigThirdPackage;
import edu.note.spring.config.SpringConfigThirdPkgWithValue;

/**
 * @author jackylee
 * @date 2025-10-05 15:28
 */
public class ThirdPackageInjectTest {
    @Test
    @DisplayName("测试第三方包")
    // 主要有三种方法
    // 1. ComponentScan("第三方包")
    void testThirdPackageWithValue() {
        // 不适用 value
        ApplicationContext context1 = new AnnotationConfigApplicationContext(SpringConfigThirdPackage.class);
        DataSource ds1 = context1.getBean(DataSource.class);
        Assertions.assertNotNull(ds1);
        // 存在 value
        ApplicationContext context2 = new AnnotationConfigApplicationContext(SpringConfigThirdPkgWithValue.class);
        DataSource ds2 = context2.getBean(DataSource.class);
        Assertions.assertNotNull(ds2);
    }

}
