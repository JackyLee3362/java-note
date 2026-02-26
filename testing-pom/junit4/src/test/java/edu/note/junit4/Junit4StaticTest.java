package edu.note.junit4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author jackylee
 * @date 2026-01-28 11:19
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Calculator.class })
public class Junit4StaticTest {

    @Test
    public void test01() {
        // 静态方法 Mock
        PowerMockito.mockStatic(Calculator.class);
        PowerMockito.when(Calculator.add(1, 2)).thenReturn(100);

        int result = Calculator.add(1, 2);
        assertEquals(100, result);
    }

}
