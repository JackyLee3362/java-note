package edu.note.web.servlet2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-24 22:01
 */
@Slf4j
@WebServlet("/user/http")
public class UserHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求行数据 比如 GET /myservlet/user?name=foo HTTP/1.1
        // 获取请求方式 GET
        System.out.println("servelt ... with http-servlet");
        log.info("请求方式是 {}", req.getMethod());
        // 获取虚拟目录 /myservlet
        log.info("虚拟目录是 {}", req.getContextPath());
        // 获取 Servlet 路径 /user
        log.info("获取 Servlet 路径 {}", req.getServletPath());
        // 获取 GET 方式的请求参数 name=foo
        log.info("获取请求参数 {}", req.getQueryString());
        // 获取 URI /myservlet
        log.info("获取 URI {}", req.getRequestURI());
        // 获取 URL http://localhost:8080/myservelt/user
        log.info("获取 URL {}", req.getRequestURL());
        // 获取协议和版本号 HTTP/1.1
        log.info("获取协议和版本号 {}", req.getProtocol());
        // 获取客户端 IP 地址
        log.info("获取客户端 IP 地址 {}", req.getRemoteAddr());
        
        // 获取请求头
        // 接收请求参数
        log.info("获取请求头", req.getHeaderNames());
        // 2. 产生响应
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.write("{\"module\": \"servlet select\"}");

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取流
        // getReader 处理字符输入流
        // getInputStream 获取字节输入流
        this.doGet(req, resp);
    }
}
