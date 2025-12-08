package edu.note;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jackylee
 * @date 2025/7/7 09:19
 */
@SpringBootApplication
public class StartApp {

    private static final Logger log = LoggerFactory.getLogger(StartApp.class);

    @Resource
    private MyDataBaseConfig myDataBaseConfig;

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
        log.info("服务启动成功！");
    }
}