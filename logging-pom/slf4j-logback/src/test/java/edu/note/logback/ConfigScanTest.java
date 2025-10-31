package edu.note.logback;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-28 13:51
 */
@Slf4j
public class ConfigScanTest {
    
    @Test
    @DisplayName("指定日志配置文件")
    void test01() {
        log.info("hello");
    }
    
}
