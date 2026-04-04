package java.edu.note.validation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jackylee
 * @date 2026-04-04 13:18
 */
public class ValidationAnnotationTest {

    @Data
    @AllArgsConstructor
    private static class User {
        @NotNull(message = "用户名不能为空")
        private String username;

        @Min(value = 18, message = "年龄必须大于等于18")
        private int age;
    }

    @Test
    @DisplayName("测试")
    void test01() {
        
        User user = new User(null, 17);


        
    }

}
