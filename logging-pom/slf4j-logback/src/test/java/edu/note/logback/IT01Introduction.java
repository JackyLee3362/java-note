package edu.note.logback;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * @author jackylee
 * @date 2024/11/28 下午12:30
 */
public class IT01Introduction {

    @Test
    @DisplayName("基础用法")
    void testBasic() {
        Logger logger = LoggerFactory.getLogger("chapters.introduction.HelloWorld1");
        logger.debug("Hello world.");
    }

    @Test
    @DisplayName("日志框架内部状态 打印上下文/状态信息")
    void testInternalState() {
        // print internal state
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }

    @Test
    @DisplayName("根日志")
    void testRootLogger() {
        Logger log = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        log.info("I'm root logger");
        log.warn("warning");
    }

}
