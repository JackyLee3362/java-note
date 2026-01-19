package edu.note;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@MapperScans({ @MapperScan("edu.note.mapper"), // 手动写的 mapper
// @MapperScan("edu.note.generate.mapper") // 如果生成 mapper 需要对其包扫描
})

public class StartApp {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
        log.info("服务启动成功");
    }

}
