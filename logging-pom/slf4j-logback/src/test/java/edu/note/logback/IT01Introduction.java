package edu.note.logback;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jackylee
 * @date 2024/11/28 下午12:30
 */
@Slf4j
public class IT01Introduction {

    @Test
    void testBasic() {
        String firstName = "Foo";
        String lastName = "Bar";
        log.info("hello, {} {}.", firstName, lastName);
    }


    @Test
    void testRootLogger() {
        Logger log = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        log.info("I'm root logger");
        log.warn("warning");
    }

}
