package edu.note.logback;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jackylee
 * @date 2024/11/28 下午10:09
 */
@SetLogbackConfigFile
public class IT03ConfigurationErrorConfig {

    static {
        LogbackConfigAnnoInitializer.initialize(IT03ConfigurationErrorConfig.class);
    }

    private static final Logger log = LoggerFactory.getLogger(IT03ConfigurationErrorConfig.class);

    @Test
    @DisplayName("打印上下文")
    void test2() {
        // 配置信息错误，会自动打印上下文
    }
}
