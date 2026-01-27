package edu.note.camel.basic;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-10-30 23:02
 */
public class DirectToFileTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .log("hello, to file...")
                        .to("file:data/message");
                // 会创建一个 message 的文件夹并随机生成文件名传输
            }
        };
    }

    @Test
    @DisplayName("测试")
    void test01() {
        context.createProducerTemplate().sendBody("direct:start", "hi...");
    }

}
