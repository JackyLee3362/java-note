package edu.note.mybatis.config;

import java.io.IOException;
import java.io.Reader;
import java.util.Collection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.h2.engine.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/28 16:40
 */
public class TypeHandlerTest {

    @Test
    @DisplayName("类别处理器 - 文件配置")
    void test01() throws IOException {
        Reader reader = Resources.getResourceAsReader("config/mybatis-config-type-handler.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        Configuration config = factory.getConfiguration();
        TypeHandlerRegistry registry = config.getTypeHandlerRegistry();
        Collection<TypeHandler<?>> typeHandlers = registry.getTypeHandlers();
        // 集合中一定有 ExampleTypeHanlder
        Assertions.assertTrue(typeHandlers.stream().anyMatch(o -> o instanceof UserTypeHandler));
        // Assertions.assertTrue(registry.getTypeHandler(User.class) instanceof
        // UserTypeHandler);
    }
}
