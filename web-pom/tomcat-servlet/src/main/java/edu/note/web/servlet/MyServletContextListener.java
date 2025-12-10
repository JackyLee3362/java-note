package edu.note.web.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
// 传统的是使用 web.xml 配置监听器，Servlet 3.0 以后支持注解配置
@WebListener
// 用于监听 Web 应用（Servlet 容器）生命周期的事件，即：Web 应用启动和关闭时自动执行自定义逻辑。
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("web 应用启动, 初始化资源...");
        // ServletContextListener.super.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("web 应用关闭, 释放资源...");
        // ServletContextListener.super.contextDestroyed(sce);
    }
}
