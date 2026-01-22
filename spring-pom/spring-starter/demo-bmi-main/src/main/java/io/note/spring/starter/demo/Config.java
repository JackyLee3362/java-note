package io.note.spring.starter.demo;

import edu.note.spring.starter.autoconfig.User;
import edu.note.spring.starter.autoconfig.UserBmiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jackylee
 * @date 2025/7/26 12:06
 */
@Configuration
public class Config {

    @Bean
    UserBmiService userBmiService() {
        User user = new User();
        user.setHeight(1.78);
        user.setName("zhang-san");
        user.setAge(28);
        user.setWeight(78.0);
        UserBmiService userBmiService = new UserBmiService();
        userBmiService.setUser(user);
        return userBmiService;
    }

}
