package edu.note;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import edu.note.domain.MyUser;
import edu.note.generate.domain.User;
import edu.note.generate.mapper.UserMapper;
import edu.note.mapper.UserAnnoMapper;
import edu.note.mapper.UserXmlMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@MapperScans({ @MapperScan("edu.note.mapper"), // 手动写的 mapper
        @MapperScan("edu.note.generate.mapper") // 对生成的 mapper 扫描
})

public class StartApp implements ApplicationListener<ContextRefreshedEvent> {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
    }

    @Resource
    private UserAnnoMapper userAnnoMapper;

    @Resource
    private UserXmlMapper userXmlMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        MyUser user = userAnnoMapper.selectById(1);
        log.info("userAnnoMapper 执行: {}", user);
        MyUser user2 = userXmlMapper.selectById(1);
        log.info("userXmlMapper 执行: {}", user2);
        User user3 = userMapper.selectByPrimaryKey(1L);
        log.info("userMapper 执行: {}", user3);

    }

}
