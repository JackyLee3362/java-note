package edu.note.web.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import lombok.extern.slf4j.Slf4j;

/**
 * servlet3.0 以后支持注解配置，无需 web.xml，故命名为 web.xml.bak
 * java6 支持 servlet 3.0
 * java8 支持 servlet 4.0
 * @author jackylee
 * @date 2025-10-23 22:55
 */
@Slf4j
@WebServlet("/user")
public class UserSelectServlet implements Servlet {

    @Override
    public ServletConfig getServletConfig() {
        // servlet 的配置对象，了解即可
        return null;
    }

    @Override
    public String getServletInfo() {
        // 获取 servlet 的信息，比如版本信息等，了解即可
        return null;
    }

    @Override
    public void init(ServletConfig arg0) throws ServletException {
        // 只会在创建时执行
        System.out.println("init servlet...");
        return;
    }

    @Override
    public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
        // 每次服务时都执行
        System.out.println("hello, servlet");
    }

    @Override
    public void destroy() {
        // 服务器正常关闭时执行，执行一次
        System.out.println("destory servlet...");
        return;
    }

}
