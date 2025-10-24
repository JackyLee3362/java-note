package edu.note.spring.context;

import java.lang.reflect.Field;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jackylee
 * @date 2025-10-15 10:44
 */
public class SpringContextTest {
    
    @Test
    @DisplayName("测试获取 Bean")
    void test01() throws Exception {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);;
        ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();
        Field field = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");;
        field.setAccessible(true);
        Map<String, Object> map = (Map<String, Object>)field.get(beanFactory);
        map.entrySet().stream().filter(e -> e.getKey().startsWith("user")).forEach(e->{
            System.out.println(e.getKey()+"="+e.getValue());
        });
    }
}
