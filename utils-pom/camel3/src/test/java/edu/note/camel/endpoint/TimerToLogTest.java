package edu.note.camel.endpoint;

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
 * @date 2025-10-11 17:47
 */
public class TimerToLogTest extends CamelTestSupport {

    @Test
    @DisplayName("基础")
    void test01() {
        NotifyBuilder notify = new NotifyBuilder(context).whenCompleted(1).create();
        assertTrue(notify.matches(20, TimeUnit.SECONDS), "1 message should be completed");
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        RoutesBuilder router = new RouteBuilder() {
            @Override
            public void configure() {
                // timer 是定时器组件, foo 是定时器名称
                from("timer:foo?period=1000")
                        .log("Hello Camel");
            }
        };
        return router;

    }

}
