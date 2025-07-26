package edu.note.spring.bean;

import javax.annotation.Resource;
import edu.note.spring.bean.MyController.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * @author jackylee
 * @date 2025/7/23 17:06
 */
@SpringBootTest(classes = Main.class)
public class ITSpringBean01 {

    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void test01() {
        // 测试 @Component 内部的 @Bean 是否会被初始化，结论是会
        User user = (User) applicationContext.getBean("admin");
        System.out.println(user);
        applicationContext.getBeansOfType(MyController.class).forEach((k, v) -> {
            System.out.println(k);
        });

    }

}
