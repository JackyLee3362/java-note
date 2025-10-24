package edu.note.mybatis.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.mybatis.model.User;

/**
 * @author jackylee
 * @date 2025/9/28 16:40
 */
public class TypeAliasTest {
    @Test
    @DisplayName("类别名 - 代码配置")
    void test01() {
        Configuration config = new Configuration();
        TypeAliasRegistry registry = config.getTypeAliasRegistry();
        registry.registerAlias("user", User.class);
        Class<?> clazz = registry.getTypeAliases().get("user");
        assertEquals("edu.note.mybatis.model.User", clazz.getName());
    }

    @Test
    @DisplayName("类别名 - 文件配置")
    void test02() throws IOException {
        Reader reader = Resources.getResourceAsReader("config/mybatis-config-type-alias.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        Configuration config = factory.getConfiguration();
        TypeAliasRegistry registry = config.getTypeAliasRegistry();
        Class<?> clazz = registry.getTypeAliases().get("user");
        assertEquals("edu.note.mybatis.model.User", clazz.getName());
    }

    @Test
    @DisplayName("包别名 - 配置")
    void test03() throws IOException {
        Configuration config = new Configuration();
        TypeAliasRegistry registry = config.getTypeAliasRegistry();
        registry.registerAliases("edu.note.mybatis.model");
        Class<?> clazz = registry.getTypeAliases().get("user");
        assertEquals("edu.note.mybatis.model.User", clazz.getName());
    }

    @Test
    @DisplayName("包别名 - 文件配置")
    void test04() throws IOException {
        Reader reader = Resources.getResourceAsReader("config/mybatis-config-type-alias-package.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        Configuration config = factory.getConfiguration();
        TypeAliasRegistry registry = config.getTypeAliasRegistry();
        Class<?> clazz = registry.getTypeAliases().get("user");
        assertEquals("edu.note.mybatis.model.User", clazz.getName());
    }

}
