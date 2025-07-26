package edu.note.java.spi.demo;


import edu.note.java.spi.LoggerService;

public class Main {
    public static void main(String[] args) {
        LoggerService loggerService = LoggerService.getService();
        loggerService.info("你好");
        loggerService.debug("调试 -> 你好");
    }
}
