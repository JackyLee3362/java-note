package edu.note.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-04-18 18:28
 */
public class Commons02_emailValidatorTest {

    @Test
    @DisplayName("测试 email")
    void test_basic_validate_email() {
        // given:
        EmailValidator emailValidator = EmailValidator.getInstance();
        // expect:
        assert emailValidator.isValid("test@example.com");
        assert !emailValidator.isValid("invalid-email");

    }

}
