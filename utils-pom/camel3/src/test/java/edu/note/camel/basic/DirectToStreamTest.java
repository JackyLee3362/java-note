package edu.note.camel.basic;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-10-13 20:11
 */
public class DirectToStreamTest extends CamelTestSupport {

    @Test
    @DisplayName("基础 URI")
    void test01() throws Exception {
        // 发送测试消息到 direct:start
        context.createProducerTemplate().sendBody("direct:start", "Camel");
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .log("传输中")
                        // .to("log:helloLog?level=INFO")
                        .to("stream:out");
            }
        };
    }

}
