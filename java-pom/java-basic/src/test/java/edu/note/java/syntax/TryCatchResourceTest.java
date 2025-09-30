package edu.note.java.syntax;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TryCatchResourceTest {
    /*
     * JDK7:IO流中捕获异常的写法
     */

    @Test
    @DisplayName("jdk7")
    void test1() {
        try (StringWriter sw = new StringWriter()) {
            // ...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("jdk9")
    void test2() throws FileNotFoundException {

        StringReader sr = new StringReader("Hello");
        StringWriter sw = new StringWriter();

        // JDK9 语法
        // try (sr; sw) {
        // ...
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

    }
}
