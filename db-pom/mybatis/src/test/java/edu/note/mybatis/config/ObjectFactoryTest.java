package edu.note.mybatis.config;

import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/28 16:40
 */
public class ObjectFactoryTest {
    @Test
    @DisplayName("对象工厂设置 - 代码配置")
    void test() {
        // 对象工厂处理
        Configuration config = new Configuration();
        ExampleObjectFactory exampleObjectFactory = new ExampleObjectFactory();
        config.setObjectFactory(exampleObjectFactory);
        Assertions.assertEquals(exampleObjectFactory, config.getObjectFactory());
    }

}
