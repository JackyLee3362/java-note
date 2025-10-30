package edu.note.camel.basic;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-10-11 17:26
 */
public class DirectToLogTest extends CamelTestSupport {

    @Test
    @DisplayName("测试")
    void test01() throws Exception {
        // 启动 Camel 上下文
        context.start();

        // 发送测试消息到 direct:start
        context.createProducerTemplate().sendBody("direct:start", "Camel");

        // 允许一段时间处理消息 然后关闭上下文
        Thread.sleep(2000);
        context.stop();
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("direct:start")
                        // .transform().simple("Hello ${body}!")
                        .to("log:directToLogExample?level=WARN");
            }
        };

    }

}
