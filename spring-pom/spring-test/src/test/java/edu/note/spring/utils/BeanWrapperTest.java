package edu.note.spring.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import edu.note.spring.model.UserDTO;


/**
 * @author jackylee
 * @date 2025-12-08 16:04
 */
public class BeanWrapperTest {
    @Test
    @DisplayName("测试")
    void test01() {
        // given:
        UserDTO dto = new UserDTO();
        BeanWrapper beanWrapper = new BeanWrapperImpl(dto);

        // when:
        beanWrapper.setPropertyValue("name", "foo");

        // then:
        assertEquals(dto.getName(), "foo");
    }

}
