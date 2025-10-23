package edu.note.web.servlet2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-23 22:55
 */
@Slf4j
public class UserSelectServlet implements Servlet {
    @Override
    public void destroy() {
        return;
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void init(ServletConfig arg0) throws ServletException {
        return;
    }

    @Override
    public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
        System.out.println("hello, servlet");
    }

    // @Override
    // protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
    // ServletException, IOException {
    // // 1. 接收请求参数
    // String name = req.getParameter("name");
    // log.info("selvlet select name -> {}", name);
    // // 2. 产生响应
    // resp.setContentType("text/json;charset=utf-8");
    // PrintWriter pw = resp.getWriter();
    // pw.write("{'module': 'servlet select'}");
    // }

    // @Override
    // protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws
    // ServletException, IOException {
    // this.doGet(req, resp);
    // }

}
