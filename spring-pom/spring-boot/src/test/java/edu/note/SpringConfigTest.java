package edu.note;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author jackylee
 * @date 2025-12-08 16:59
 */
@SpringBootTest(classes = StartApp.class)
public class SpringConfigTest {

    @Resource
    private MyDataBaseConfig myDataBaseConfig;

    @Test
    @DisplayName("测试")
    public void test01() {
        assertEquals("root", myDataBaseConfig.getDbUser());
        assertEquals("123456", myDataBaseConfig.getDbPassword());
    }

}
