package edu.note.camel.basic;

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
        // NotifyBuilder 是测试辅助工具类。
        // 用于在 Camel 集成测试时等待和检测路由消息处理的状态。
        // 帮助你编写等待消息处理完成、失败、挂起等条件的断言，
        // 非常适合用于单元测试和集成测试中对 Camel 路由的验证。
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
