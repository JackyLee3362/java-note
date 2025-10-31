package edu.note.logback.chapter;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.note.logback.LogbackConfigAnnoInitializer;
import edu.note.logback.SetLogbackConfigFile;

/**
 * @author jackylee
 * @date 2024/11/28 下午9:59
 */

@SetLogbackConfigFile("日志配置文件.xml")
public class IT02Architecture {

    static {
        LogbackConfigAnnoInitializer.initialize(IT02Architecture.class);
    }

    private final Logger log = LoggerFactory.getLogger(IT02Architecture.class);

    @Test
    void test() {
        log.info("info...");
    }

}
