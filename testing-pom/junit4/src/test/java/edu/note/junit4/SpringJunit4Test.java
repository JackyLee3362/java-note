package edu.note.junit4;

import javax.annotation.Resource;

import edu.note.junit4.MyController.User;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * @author jackylee
 * @date 2025/7/23 17:06
 */
@SpringBootTest(classes = StartApp.class)
public class SpringJunit4Test {

    @Resource
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("测试")
    void test01() {
        // 测试 @Component 内部的 @Bean 是否会被初始化，结论是会
        User user = (User) applicationContext.getBean("admin");
        Assert.assertNotNull(user);
        applicationContext.getBeansOfType(MyController.class).forEach((k, v) -> {
            System.out.println(k);
        });

    }

}
