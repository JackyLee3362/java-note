package edu.note;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import edu.note.domain.User;
import edu.note.mapper.UserAnnoMapper;
import edu.note.mapper.UserXmlMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@MapperScans({ @MapperScan("edu.note.mapper"), // 手动写的 mapper
// @MapperScan("edu.note.generate.mapper") // 如果生成 mapper 需要对其包扫描
})

public class StartApp implements ApplicationListener<ContextRefreshedEvent> {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
    }

    @Resource
    private UserAnnoMapper userAnnoMapper;

    @Resource
    private UserXmlMapper userXmlMapper;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        User user = userAnnoMapper.selectById(1);
        log.info("userAnnoMapper 执行: {}", user);
        User user2 = userXmlMapper.selectById(1);
        log.info("userXmlMapper 执行: {}", user2);

    }

}
