package edu.note;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-10-11 15:43
 */
public class StringUtilsTest {

    String desensitize(String num) {
        Integer total = StringUtils.length(num);
        String right4 = StringUtils.right(num, 4);
        String star7Right4 = StringUtils.leftPad(right4, total, "*");
        String star4Right4 = StringUtils.removeStart(star7Right4, "***");
        String concat = StringUtils.left(num, 3).concat(star4Right4);
        return concat;
    }

    @Test
    @DisplayName("测试")
    void test01() {
        assertEquals("138****1111", desensitize("13812341111"));
        assertEquals("138*****1111", desensitize("138123451111"));
    }

}
