package edu.note.spring.config;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import lombok.extern.slf4j.Slf4j;

/**
 * 该类是 SpringMVC 提供的一个抽象基类，用于以 Java 代码方式(无 web.xml) 配置和初始化应用核心 DispatcherServlet
 * 该类能被加载是因为 java 的 spi 机制
 * 
 * @author jackylee
 * @date 2025-10-25 13:53
 * @see AbstractAnnotationConfigDispatcherServletInitializer
 */
@Slf4j
public class ServletContainerInitConfig extends AbstractDispatcherServletInitializer {
    // 也可以 extends AbstractAnnotationConfigDispatcherServletInitializer

    // 加载 spring 应用配置类，全局唯一
    // 管理: 非 web 相关的 Bean，如 Service、Dao、Repository 等业务/数据层 Bean，安全组件、定时调度、工具类等。
    // 生命周期与整个 Web 应用相同，容器启动时一起初始化。
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        // 生命周期是整个应用共享的 bean，所有的 DispatchServlet 都可以访问这个 bean
        // 比如配置数据源、事务管理器等
        log.info("创建根应用上下文...");
        return null;
    }

    @Override
    // 加载 spring-mvc 配置类
    // 初始化 servlet 应用时创建 spring 容器上下文
    // 生命周期和各自的 DispatchServlet 绑定
    // web-application(spring-mvc) 上下文
    // Web 层相关的 Bean，如 Controller、HandlerMapping、ViewResolver、拦截器等。
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
