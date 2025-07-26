package edu.note.spring.starter.autoconfig;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties({User.class})
public class BmiAutoConfiguration {
    @Bean
    UserBMI userBMI(User user){
        UserBMI userBMI = new UserBMI();
        userBMI.setUser(user);
        return userBMI;
    }
}
