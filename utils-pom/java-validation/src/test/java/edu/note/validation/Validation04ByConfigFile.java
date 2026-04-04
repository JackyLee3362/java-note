package edu.note.validation;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-04-04 16:10
 */
public class Validation04ByConfigFile {

    static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    static Validator validator = factory.getValidator();
    
    @Test
    @DisplayName("测试")
    void test_basic_0() {
        // given:
        // when:
        // then:
    }
}
