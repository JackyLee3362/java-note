package edu.note.spring.starter.autoconfig;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ User.class })
public class BmiAutoConfiguration {
    @Bean
    UserBmiService userBMI(User user) {
        UserBmiService userBMI = new UserBmiService();
        userBMI.setUser(user);
        return userBMI;
    }
}
