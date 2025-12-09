package edu.note.mapstruct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.mapstruct.fieldmapping.FieldConverter;
import edu.note.mapstruct.fieldmapping.Source;
import edu.note.mapstruct.fieldmapping.Target;

/**
 * @author jackylee
 * @date 2025-11-05 14:32
 */
public class FieldConverterTest {

    FieldConverter converter = FieldConverter.INSTANCE;

    @Test
    @DisplayName("测试 Source -> Target")
    void test01() {
        Source dto = Source.builder()
                .id(1L)
                .age(12)
                .firstName("Foo")
                .build();
        Target target = converter.toTarget(dto);
        assertEquals(target.getUserId(), 1);
        assertEquals(target.getAge(), 12);
        assertEquals(target.getName(), "Foo");
        assertNotNull(target.getCreateTime());
        assertNull(target.getUserType());
    }

    @Test
    @DisplayName("测试 Target -> Source")
    void test02() {
        Target dto = Target.builder()
                .userId(1L)
                .age(12)
                .name("Foo")
                .build();
        Source user = converter.toSource(dto);
        assertEquals(user.getId(), 1);
        assertEquals(user.getAge(), 12);
        assertEquals(user.getFirstName(), "Foo");
        assertNull(user.getCreateTime());
    }

}
