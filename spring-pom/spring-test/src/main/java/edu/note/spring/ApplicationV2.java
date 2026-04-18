package edu.note.spring;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2026-04-18 20:00
 */
@Slf4j
@SpringBootApplication
public class ApplicationV2 implements ApplicationRunner {

    public static void main(String[] args) {
        System.out.println();

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("开始执行命令...");
        log.info("结束执行命令...");
    }

}
