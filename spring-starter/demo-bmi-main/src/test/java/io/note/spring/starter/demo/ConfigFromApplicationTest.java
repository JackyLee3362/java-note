package io.note.spring.starter.demo;

import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description: 测试下自动装配
 * @author: Jacky Lee
 * @date: 2024/3/23 10:24
 */
@SpringBootTest
public class ConfigFromApplicationTest {

    @Value("${demo.user.teacher.name}")
    private String name;

    @Resource
    Student student;
    @Test
    public void testConfiguration(){
        System.out.println(name);
        System.out.println(student);
    }
}
