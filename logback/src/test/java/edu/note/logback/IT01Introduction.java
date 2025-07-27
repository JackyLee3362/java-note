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
    public void testBasic() {
        String firstName = "jacky";
        String lastName = "lee";
        log.info("hello, {} {}.", firstName, lastName);
    }



    @Test
    public void testRootLogger() {
        Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.info("I'm root logger");
    }

}
