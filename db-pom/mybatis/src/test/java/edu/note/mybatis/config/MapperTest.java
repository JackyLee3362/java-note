package edu.note.mybatis.config;

import java.io.IOException;

import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.mybatis.mapper.UserMapper;

/**
 * @author jackylee
 * @date 2025-09-29 14:28
 */
public class MapperTest {

    @Test
    @DisplayName("映射器 - 代码配置")
    void test01() throws IOException {
        Configuration config = new Configuration();
        Assertions.assertFalse(config.hasMapper(UserMapper.class));
        config.addMapper(UserMapper.class);
        Assertions.assertTrue(config.hasMapper(UserMapper.class));
    }
}
