package edu.note.camel.basic;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.camel.router.CopyFileRoute;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-11 16:21
 */
@Slf4j
public class CopyFileRouteTest extends CamelTestSupport {

    @Test
    @DisplayName("测试文件传输")
    void test01() throws Exception {

        log.info("开始...");
        context.start();
        Thread.sleep(3000);
        context.stop();
        log.info("结束");
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new CopyFileRoute();
    }
}
