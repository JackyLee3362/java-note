package edu.note.spring.config;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * @author jackylee
 * @date 2025-10-25 13:53
 */
public class ServletContainerInitConfig
        extends AbstractDispatcherServletInitializer {
    // 也可以 extends AbstractAnnotationConfigDispatcherServletInitializer

    @Override
    // 初始化 servlet 应用时创建 spring 容器上下文
    protected WebApplicationContext createServletApplicationContext() {
        System.out.println("creating annotation config web application context...");
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringMvcConfig.class);
        return ctx;
    }

    @Override
    // 该方法定义了哪些请求交给 spring-mvc 管理
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }

    @Override
    // 请求 GET /user?name=张三 这样的请求时，会产生乱码，使用过滤器
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[] { filter };
    }

}
