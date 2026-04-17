package edu.note.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-04-15 10:26
 */
public class TestCommons01_Validator {
    @Test
    @DisplayName("测试 url")
    void test_basic_validate_url() {
        // given:
        UrlValidator urlValidator = new UrlValidator();
        // expect:
        assert urlValidator.isValid("http://www.baidu.com");
        assert urlValidator.isValid("https://www.google.com");
        assert urlValidator.isValid("ftp://example.com");

    }

    @Test
    @DisplayName("测试 http url")
    void test_basic_validate_http_url() {
        // given:
        UrlValidator urlValidator = new UrlValidator(new String[] { "http", "https" });
        // expect:
        assert urlValidator.isValid("http://www.baidu.com");
        assert !urlValidator.isValid("ftp://www.baidu.com");
    }

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
