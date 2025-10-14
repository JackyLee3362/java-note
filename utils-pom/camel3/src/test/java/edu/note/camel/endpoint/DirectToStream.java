package edu.note.camel.endpoint;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-10-13 20:11
 */
public class DirectToStream {

    CamelContext context;
    ProducerTemplate producerTemplate;

    @BeforeEach
    void setup() {
        context = new DefaultCamelContext();
        producerTemplate = context.createProducerTemplate();
        // 启动 Camel 上下文
        context.start();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        Thread.sleep(2000);
        // 允许一段时间处理消息 然后关闭上下文
        context.stop();
    }

    @Test
    @DisplayName("基础 URI")
    void test01() throws Exception {
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .log("传输中")
                        // .to("log:helloLog?level=INFO")
                        .to("stream:out");
            }
        });
        // 发送测试消息到 direct:start
        producerTemplate.sendBody("direct:start", "Camel");
    }

}
