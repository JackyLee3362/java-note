package edu.note.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-30 22:40
 */
@Slf4j
public class HelloWorldTest {

    @Test
    @DisplayName("测试文件传输")
    void test01() throws Exception {

        log.info("开始...");
        try (CamelContext context = new DefaultCamelContext()) {
            context.addRoutes(new RouteBuilder() {
                public void configure() {
                    // noop=true 表示是复制，false 为移动
                    from("direct:start")
                            .log("传输成功 headers=${headers}, body=${body}");
                }
            });
            context.start();
            context.createProducerTemplate().sendBody("direct:start", "hello, camel");
            Thread.sleep(3000);
            context.stop();
        }
        log.info("结束");
    }
}
