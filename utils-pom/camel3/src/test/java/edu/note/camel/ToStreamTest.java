package edu.note.camel;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-10-13 20:11
 */
public class ToStreamTest extends CamelTestSupport {

    @Test
    @DisplayName("基础")
    void test01() {
        NotifyBuilder notify = new NotifyBuilder(context).whenCompleted(1).create();
        assertTrue(notify.matches(20, TimeUnit.SECONDS), "1 message should be completed");
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("timer:foo?period=1000")
                        .log("日志");
            }
        };
    }

}
