package edu.note.java.syntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.java.model.Teacher;

public class TestOverrideEqual {
    @Test
    @DisplayName("")
    void test01() {

        // 2.创建三个学生对象
        Teacher s1 = new Teacher("zhangsan", 23);
        Teacher s2 = new Teacher("zhangsan", 23);
        Assertions.assertEquals(s1, s2);

    }
}
