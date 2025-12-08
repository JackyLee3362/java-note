package edu.note.spring.bean.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author jackylee
 * @date 2025-12-08 16:04
 */
public class BeanWrapperTest {
    @Test
    @DisplayName("测试")
    void test01() {
        // given:
        Source source = new Source();
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);

        // when:
        beanWrapper.setPropertyValue("name", "foo");

        // then:
        assertEquals(source.getName(), "foo");
    }

}
