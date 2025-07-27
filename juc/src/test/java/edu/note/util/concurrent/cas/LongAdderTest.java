package edu.note.util.concurrent.cas;

import java.util.concurrent.atomic.LongAdder;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/17 20:37
 */
public class LongAdderTest {

    @Test
    public void test() {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
    }

}
