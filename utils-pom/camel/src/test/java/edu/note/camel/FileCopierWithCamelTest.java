package edu.note.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-11 16:21
 */
@Slf4j
public class FileCopierWithCamelTest {

    @Test
    @DisplayName("测试")
    void test01() throws Exception {

        System.out.println("开始...");
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                // noop=true 表示是复制，false 为移动
                from("file:data/inbox?noop=true")
                        .to("file:data/outbox"); // (1) 将文件从inbox路由到outbox
            }
        });
        context.start();
        Thread.sleep(3000);
        context.stop();
        System.out.println("结束...");
    }
}
