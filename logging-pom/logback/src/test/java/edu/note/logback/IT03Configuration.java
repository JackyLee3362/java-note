package edu.note.logback;

import ch.qos.logback.classic.LoggerContext;
// import ch.qos.logback.core.util.StatusPrinter2;
import java.net.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jackylee
 * @date 2024/11/28 下午9:06
 */
public class IT03Configuration {

    static {
        Class<IT03Configuration> clazz = IT03Configuration.class;
        String fileName = clazz.getSimpleName() + ".xml";
        URL resource = clazz.getClassLoader().getResource(fileName);
        if (resource != null) {
            String logConfig = resource.getPath();
            System.setProperty("logback.configurationFile", logConfig);
        }
    }

    private final Logger log = LoggerFactory.getLogger(IT03Configuration.class);

    @Test
    @DisplayName("读取自己的配置")
    void test1() {
        log.info("123");
    }

    @Test
    @DisplayName("打印上下文/状态信息")
    void test2() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        // TODO 更高版本存在
        // new StatusPrinter2().print(context);
    }

}
