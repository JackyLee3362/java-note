package edu.note.java.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EnumTest {

    static enum Color {
        RED,
        YELLOW,
        BLUE;

        public static Color getOrNull(String value) {
            try {
                return Color.valueOf(value);
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Test
    @DisplayName("测试枚举的基本用法")
    void test01() {
        Color userType = Color.RED;
        assertEquals(userType.name(), "RED");
        assertEquals(Color.RED, Color.valueOf("RED"));
        Color.valueOf("RED");
    }

    @Test
    @DisplayName("枚举静态获取")
    void test02() {
        assertEquals(Color.getOrNull("RED"), Color.RED);
        assertEquals(Color.getOrNull("GREEN"), null);
    }
}
