package edu.note.mybatis.config;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.tools.RunScript;
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

    @Test
    @DisplayName("映射器 - 代码配置 + 映射文件")
    void test02() throws IOException {
        Configuration config = new Configuration();
        Reader mapperReader = Resources.getResourceAsReader("mapper/UserMapper.xml");
        new XMLMapperBuilder(mapperReader, config, "UserMapper.xml", config.getSqlFragments()).parse();
        Assertions.assertTrue(config.hasMapper(UserMapper.class));
    }

    @Test
    @DisplayName("映射器 - 文件配置")
    void test03() throws IOException {
        Reader reader = Resources.getResourceAsReader("config/mybatis-config-mapper.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        Configuration config = factory.getConfiguration();
        Assertions.assertTrue(config.hasMapper(UserMapper.class));
    }
}
