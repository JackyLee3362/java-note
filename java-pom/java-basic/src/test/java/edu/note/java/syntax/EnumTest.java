package edu.note.java.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EnumTest {
    static enum UserType {
        ADMIN,
        CUSTOMER,
        GUEST
    }

    @Test
    @DisplayName("测试枚举的基本用法")
    void test01() {
        UserType userType = UserType.ADMIN;
        assertEquals(userType.name(), "ADMIN");
        assertEquals(UserType.ADMIN, UserType.valueOf("ADMIN"));
        UserType.valueOf("admin");
    }
}
