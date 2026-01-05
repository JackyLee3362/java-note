package edu.note.java.annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.note.java.model.Student;

/**
 * @author jackylee
 * @date 2025/7/15 14:43
 */
public class PostConstructTest {

    @Test
    void testPostConstruct() {
        Student student = new Student();
        // TODO 是不是只能配合 Spring 完成
        assertEquals("default", student.getName());
    }

}
