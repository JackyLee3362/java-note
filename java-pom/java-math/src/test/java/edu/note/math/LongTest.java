package edu.note.math;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/24 20:07
 */
public class LongTest {

    @Test
    void test01() {
        long d = 1000_0086_6406L;
        long a = 100_0086_6406L;
        String string = "53" + "250924" + String.valueOf(d).substring(1);
        Assertions.assertEquals(19, string.length());
        String yyMMdd = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        System.out.println(yyMMdd);
    }

}
