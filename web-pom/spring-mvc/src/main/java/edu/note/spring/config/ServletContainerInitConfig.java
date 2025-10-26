package edu.note.spring.config;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import lombok.extern.slf4j.Slf4j;

/**
 * 该类能被加载是因为 java 的 spi 机制
 * 
 * @author jackylee
 * @date 2025-10-25 13:53
 * @see AbstractAnnotationConfigDispatcherServletInitializer
 */
@Slf4j
public class ServletContainerInitConfig extends AbstractDispatcherServletInitializer {
    // 也可以 extends AbstractAnnotationConfigDispatcherServletInitializer
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        // 生命周期是整个应用共享的 bean，所有的 DispatchServlet 都可以访问这个 bean
        // 比如配置数据源、事务管理器等
        log.info("创建根应用上下文...");
        return null;
    }

    @Override
    // 初始化 servlet 应用时创建 spring 容器上下文
    // web-application(spring-mvc) 上下文
    protected WebApplicationContext createServletApplicationContext() {
        log.info("创建 web 应用上下文");
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringMvcConfig.class);
        return ctx;
    }

    @Override
    // 该方法定义了哪些请求交给 spring-mvc 管理
    protected String[] getServletMappings() {
        log.info("设置 servlet 映射...");
        return new String[] { "/" };
    }

    @Override
    // 请求 GET /user?name=张三 这样的请求时，会产生乱码，使用过滤器
    protected Filter[] getServletFilters() {
        log.info("设置 filter...");
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[] { filter };
    }

}
