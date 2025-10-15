package edu.note;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.annotation.Resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.note.spring.UserService;

/**
 * @author jackylee
 * @date 2025-10-14 20:19
 */
@SpringBootTest
public class StartAppTest {

    @Resource
    private UserService userService;

    @Test
    @DisplayName("测试")
    void test01() {
        assertNotNull(userService);
    }

}
