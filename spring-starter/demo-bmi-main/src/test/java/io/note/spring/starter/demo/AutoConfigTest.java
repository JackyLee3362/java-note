package io.note.spring.starter.demo;

import javax.annotation.Resource;
import edu.note.spring.starter.autoconfig.UserBMI;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AutoConfigTest {

    @Resource
    UserBMI userBMI;

    @Test
    public void testMyBmi(){
        userBMI.calculateBMI();
    }

}