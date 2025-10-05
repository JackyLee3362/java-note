package edu.note.spring.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import edu.note.spring.HelloDao;

/**
 * @author jackylee
 * @date 2025-09-30 14:49
 */
public class BeanFactoryTest {

    @Test
    @DisplayName("测试层次结构")
    void testStructor() {
        // ApplicationContext 继承 BeanFactory
        // BeanFactory是IoC容器的顶层接口，初始化BeanFactory对象时，加载的bean延迟加载
    }

    @Test
    @DisplayName("测试 bean-facotry")
    void testIOC01() {
        // - BeanFactory是延迟加载:只有在获取bean对象的时候才会去创建
        // - ApplicationContext是立即加载:容器加载的时候就会创建bean对象
        Resource resources = new ClassPathResource("");
        BeanFactory bf = new XmlBeanFactory(resources);
        HelloDao bean = bf.getBean(HelloDao.class);
        bean.save("foo");
        // - ApplicationContext要想成为延迟加载:只需要按照如下方式进行配置
        // <bean id=... class=... lazy-init="true"/>
    }
}
