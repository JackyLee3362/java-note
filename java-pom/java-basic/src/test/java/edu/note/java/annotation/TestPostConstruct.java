package edu.note.java.annotation;

import edu.note.java.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/7/15 14:43
 */
public class TestPostConstruct {

    @Test
    void testPostConstruct() {
        Student student = new Student();
        // TODO 是不是只能配合 Spring 完成
        Assertions.assertEquals("default", student.getName());
    }

}
