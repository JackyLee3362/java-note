package edu.note.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-10-11 17:26
 */
public class DirectMessageTest {

    @Test
    @DisplayName("测试")
    void test01() throws Exception {
        CamelContext context = new DefaultCamelContext();
        RouteBuilder router = new RouteBuilder() {
            public void configure() {
                from("direct:start")
                        .transform().simple("Hello ${body}")
                        .to("mock:result");
            }
        };
        context.addRoutes(router);
        context.start();
        context.stop();
    }

}
