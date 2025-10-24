package edu.note.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-10-14 12:40
 */
public class MyCustomeTest {
    @Test
    @DisplayName("测试")
    void test01() throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addComponent("myCustom", new MyCustomComponent());
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("myCustom:foo")
                        .to("log:myCustomLog?level=INFO");
            }
        });

        // 启动 Camel 上下文
        context.start();

        // 允许一段时间处理消息然后关闭上下文
        Thread.sleep(2000);
        context.stop();
    }
}
