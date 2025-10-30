package edu.note.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.RouteDefinition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-30 20:05
 */
@Slf4j
public class ContextTest {

    CamelContext camelContext = new DefaultCamelContext();

    @Test
    @DisplayName("增加 Component")
    void test01() {
        RouteBuilder routeBuilder = new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // 路由定义
                RouteDefinition start = from("direct:foo").id("foo");
                // 定义 choice
                start.choice().when(exchange -> {
                    String message = exchange.getIn().getBody(String.class);
                    log.info("exchange:{}", message);
                    return "next".equals(message);
                });
                start.end();
            }
        };

        // 路由定义
        // ProcessorDefinition<RouteDefinition> start =
        // routeBuilder.from("direct:foo").id("foo");
        // 甚至可以这样定义
        // start.choice().when(simple("${body} == 'xxx'"))
        // .to("direct:bar");
        // TODO: endChoice()
        // TODO: endDoTry()
        // TODO: endParent()
        // TODO: endHystrix()
        // TODO: endRest()

    }

}
