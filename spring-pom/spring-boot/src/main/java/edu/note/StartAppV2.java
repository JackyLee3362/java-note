
package edu.note;

import javax.annotation.Resource;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2026-01-14 17:04
 */
@Slf4j
@SpringBootApplication
public class StartAppV2 {

    @Resource
    private MyDataBaseConfig myDataBaseConfig;

    public static void main(String[] args) {
        SpringApplication.run(StartAppV2.class, args);
        log.info("服务启动成功！");
    }

}