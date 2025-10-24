package edu.note.java.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EqualTest {
    @Test
    @DisplayName("")
    void test01() {

        // 2.创建三个学生对象
        Teacher s1 = new Teacher("zhangsan", 23);
        Teacher s2 = new Teacher("zhangsan", 23);
        assertEquals(s1, s2);

    }
}
