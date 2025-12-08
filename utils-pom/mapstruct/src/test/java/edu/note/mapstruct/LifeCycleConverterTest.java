package edu.note.mapstruct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.mapstruct.lifecycle.LifeCycleConverter;
import edu.note.mapstruct.lifecycle.Source;
import edu.note.mapstruct.lifecycle.Target;

/**
 * @author jackylee
 * @date 2025-12-08 15:46
 */
public class LifeCycleConverterTest {

    LifeCycleConverter converter = LifeCycleConverter.INSTANCE;

    @Test
    @DisplayName("测试 BeforeMapping 和 AfterMapping")
    void test01() {
        Source dto = Source.builder()
                .id(1L)
                .age(12)
                .firstName("Foo")
                .lastName("Bar")
                .build();
        Target target = converter.toTarget(dto);
        assertEquals(target.getUserId(), 1);
        assertEquals(target.getAge(), 12);
        assertEquals(target.getName(), "FooBar");
        assertNotNull(target.getCreateTime());
    }

}
