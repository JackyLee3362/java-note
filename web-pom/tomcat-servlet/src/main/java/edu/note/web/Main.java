package edu.note.web;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import edu.note.web.servlet.HelloHttpServlet;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-12-10 10:44
 */
@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);
        // tomcat.getHost().setAppBase(docBase);
        File file = new File(".");
        log.info("文件路径:{}", file.getAbsolutePath());
        log.info("启动端口:{}", tomcat.getConnector().getPort());

        // 建立一个Web应用上下文
        // Context context = tomcat.addWebapp("", new File(".").getAbsolutePath());
        Context context = tomcat.addContext("", file.getAbsolutePath());

        // 添加Servlet和Servlet映射
        Tomcat.addServlet(context, "helloServlet", new HelloHttpServlet());
        // Tomcat.addServlet(context, "userServlet", new UserServlet());
        context.addServletMappingDecoded("/hello", "helloServlet");
        // context.addServletMappingDecoded("/user", "helloServlet");

        tomcat.start();
        tomcat.getServer().await(); // 保持服务运行
    }
}
