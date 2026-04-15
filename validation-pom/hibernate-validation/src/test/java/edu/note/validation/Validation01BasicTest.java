package edu.note.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jackylee
 * @date 2026-04-04 13:18
 */
public class Validation01BasicTest {

    static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    static Validator validator = factory.getValidator();

    @Data
    @AllArgsConstructor
    private static class User {
        @NotNull(message = "用户名不能为空")
        @Size(min = 2, max = 20, message = "用户名长度必须在2到20之间")
        private String username;

        @Min(value = 18, message = "年龄必须大于等于18")
        private int age;
    }

    @Test
    @DisplayName("测试基本校验")
    void test_basic_01() {
        // given:
        User user = new User(null, 17);
        // when:
        Set<ConstraintViolation<User>> validate = validator.validate(user);
        // then:
        for (ConstraintViolation<User> violation : validate) {
            System.out.println(violation.getMessage());
        }
    }


}
