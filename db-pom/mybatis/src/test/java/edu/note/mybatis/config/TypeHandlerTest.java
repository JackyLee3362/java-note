package edu.note.mybatis.config;

import java.io.IOException;
import java.io.Reader;
import java.util.Collection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.mybatis.model.User;

/**
 * @author jackylee
 * @date 2025/9/28 16:40
 * @desc https://www.cnblogs.com/dw3306/p/16821591.html
 */
public class TypeHandlerTest {

    @Test
    @DisplayName("基础类别处理器 - 代码配置")
    void test01() throws IOException {
        Configuration config = new Configuration();
        TypeHandlerRegistry registry = config.getTypeHandlerRegistry();
        registry.register(ExampleTypeHandler.class);
        Collection<TypeHandler<?>> typeHandlers = registry.getTypeHandlers();
        // 集合中一定有 ExampleTypeHanlder
        Assertions.assertTrue(typeHandlers.stream().anyMatch(o -> o instanceof ExampleTypeHandler));
    }

    @Test
    @DisplayName("基础类别处理器 - 文件配置")
    void test02() throws IOException {
        Reader reader = Resources.getResourceAsReader("config/mybatis-config-type-handler.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        Configuration config = factory.getConfiguration();
        TypeHandlerRegistry registry = config.getTypeHandlerRegistry();
        Collection<TypeHandler<?>> typeHandlers = registry.getTypeHandlers();
        // 集合中一定有 ExampleTypeHanlder
        Assertions.assertTrue(typeHandlers.stream().anyMatch(o -> o instanceof ExampleTypeHandler));
    }

    @Test
    @DisplayName("自定义类型处理器 - 代码配置")
    void test03() {
        Configuration config = new Configuration();
        TypeHandlerRegistry registry = config.getTypeHandlerRegistry();
        registry.register(User.class, UserTypeHandler.class);
        Collection<TypeHandler<?>> typeHandlers = registry.getTypeHandlers();
        // 集合中一定有 UserTypeHandler
        Assertions.assertTrue(typeHandlers.stream().anyMatch(o -> o instanceof UserTypeHandler));
        // Assertions.assertTrue(registry.hasTypeHandler(User.class));
        Assertions.assertTrue(registry.hasTypeHandler(User.class));
    }

    @Test
    @DisplayName("泛型处理器 - 代码配置")
    void test04() {
        Configuration config = new Configuration();
        TypeHandlerRegistry registry = config.getTypeHandlerRegistry();
        registry.register(User.class, GenericTypeHandler.class);
        Collection<TypeHandler<?>> typeHandlers = registry.getTypeHandlers();
        // 集合中一定有 UserTypeHandler
        Assertions.assertTrue(typeHandlers.stream().anyMatch(o -> o instanceof GenericTypeHandler));
        // Assertions.assertTrue(registry.hasTypeHandler(User.class));
        Assertions.assertTrue(registry.hasTypeHandler(User.class));
    }
}
