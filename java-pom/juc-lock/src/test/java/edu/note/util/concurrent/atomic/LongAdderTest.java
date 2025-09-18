package edu.note.util.concurrent.atomic;

import java.util.concurrent.atomic.LongAdder;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/17 20:37
 */
public class LongAdderTest {

    @Test
    void test01() {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
    }

}
