package edu.note.web.servlet;

import java.io.BufferedReader;
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
@WebServlet("/hello")
public class HelloHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求行数据 比如 GET /myservlet/user?name=foo HTTP/1.1
        // 获取请求方式 GET
        log.info("请求方式: {}", req.getMethod());
        // 获取虚拟目录 /myservlet
        log.info("虚拟目录: {}", req.getContextPath());
        // 获取 Servlet 路径 /user
        log.info("获取 Servlet 路径: {}", req.getServletPath());
        // 获取 GET 方式的请求参数 name=foo
        log.info("获取请求参数: {}", req.getQueryString());
        // 获取 URI /myservlet
        log.info("获取 URI: {}", req.getRequestURI());
        // 获取 URL http://localhost:8080/myservelt/user
        log.info("获取 URL: {}", req.getRequestURL());
        // 获取协议和版本号 HTTP/1.1
        log.info("获取协议和版本号: {}", req.getProtocol());
        // 获取客户端 IP 地址
        log.info("获取客户端 IP 地址: {}", req.getRemoteAddr());

        // 2.获取请求头
        // 接收请求参数
        log.info("获取请求头:", req.getHeaderNames());

        // 3.其他功能
        // - 获取请求参数通用方式
        log.info("获取请求参数name: {}", req.getParameter("name"));
        // - 请求转发 (只能转发当前服务器内部资源)
        // req.getRequestDispatcher(待转发路径).forward(req, resp);
        // - 共享数据
        // req.setAttribute 存储数据用于共享
        // - 获取 ServletContext

        // 4. 产生响应
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.write("{\"module\": \"servlet select\"}");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 3.获取请求体
        // 如果带中文，需要设置编码
        req.setCharacterEncoding("utf-8");

        // 获取流
        // getReader 处理字符输入流
        // getInputStream 获取字节输入流
        BufferedReader reader = req.getReader();
        String line = null;
        while ((line = reader.readLine()) != null) {
            log.info("读取行: {}",line);
        }
    }
}
