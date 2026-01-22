package io.note.spring.starter.demo;

import javax.annotation.Resource;
import edu.note.spring.starter.autoconfig.UserBmiService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AutoConfigTest {

    @Resource
    UserBmiService userBMI;

    @Test
    void testMyBmi() {
        userBMI.calculateBMI();
    }

}