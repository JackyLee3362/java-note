package edu.note.java.syntax;

import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/12 10:40
 */
public class TestNull {

    @Test
    void test() {
        fn(null);
        fn(true);
        fn(false);
    }

    void fn(Boolean s){
        if (s != null && s) {
            System.out.println("s is true");
        } else {
            System.out.println("s is false");
        }
    }

}
