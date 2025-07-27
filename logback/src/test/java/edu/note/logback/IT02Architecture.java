package edu.note.logback;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jackylee
 * @date 2024/11/28 下午9:59
 */

@ConfigFile
public class IT02Architecture {

    static {
        LogbackConfigAnnoInitializer.initialize(IT02Architecture.class);
    }

    private final Logger log = LoggerFactory.getLogger(IT02Architecture.class);

    @Test
    public void test() {
        log.info("info...");
    }

}
