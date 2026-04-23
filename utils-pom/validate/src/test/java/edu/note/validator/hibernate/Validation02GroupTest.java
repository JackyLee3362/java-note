package edu.note.validator.hibernate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jackylee
 * @date 2026-04-04 15:31
 */
public class Validation02GroupTest {

    static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    static Validator validator = factory.getValidator();

    private static interface GroupValidateAdd {
    }

    private static interface GroupValidateUpdate {
    }

    @Data
    @AllArgsConstructor
    private static class Request {

        @Null(message = "ID必须为空", groups = GroupValidateAdd.class)
        @NotNull(message = "ID不能为空", groups = GroupValidateUpdate.class)
        private Integer id;

        @NotNull(message = "用户名不能为空", groups = GroupValidateAdd.class)
        @Null(message = "用户名需要为空")
        @Size(min = 2, max = 20, message = "用户名长度必须在2到20之间")
        private String username;

        @Min(value = 18, message = "年龄必须大于等于18")
        private int age;
    }

    @Test
    @DisplayName("测试分组校验")
    // 当 Default 和 GroupValidateAdd 都存在时，GroupValidateAdd 的校验规则会覆盖 Default 的校验规则
    void test_group_01() {
        // given:
        Request request = new Request(1, null, 17);
        // when:
        Set<ConstraintViolation<Request>> validate = validator.validate(request, GroupValidateAdd.class, Default.class);
        // then:
        for (ConstraintViolation<Request> violation : validate) {
            System.out.println(violation.getMessage());
        }
    }
}
