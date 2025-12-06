package edu.note.mapstruct;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-11-05 14:32
 */
public class UserMapperTest {

    BasicConverter converter = BasicConverter.INSTANCE;

    @Test
    @DisplayName("测试")
    void test01() {
        Target dto = new Target(1L, "Foo", "Bar", 12, "foo@bar.com", "baz city");
        Source user = converter.toUser(dto);
        assertEquals(user.getId(), 1);
        assertEquals(user.getAge(), 12);
        assertEquals(user.getName(), "Foo");
        // assertNotNull(user.getCreateTime());
        // assertNotNull(user.getUpdateTime());
    }
}
