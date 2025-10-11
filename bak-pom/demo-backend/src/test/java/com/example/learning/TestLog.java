package com.example.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {

    public static void printLog(){
        Logger logger = LoggerFactory.getLogger(TestLog.class);
        logger.info("info level");
        logger.debug("debug level");
        logger.error("error level");
    }
}
