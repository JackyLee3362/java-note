package edu.note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class StartApp {
    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
        log.info("服务启动成功");
    }
}