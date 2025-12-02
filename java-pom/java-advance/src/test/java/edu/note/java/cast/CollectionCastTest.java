package edu.note.java.cast;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-11-28 11:39
 */
public class CollectionCastTest {

    void f1(List<Object> list) {
        return;
    }

    void f2(List<?> list) {
        return;
    }

    void f3(List<? extends Object> list) {
        return;
    }

    void f4(List<? super Object> list) {
        return;
    }

    @Test
    @DisplayName("测试")
    void test01() {
        // given:
        List<String> list = new ArrayList<>();

        // and:
        // f1(list); // 编译错误
        // List<Object> f1Arg = list; // 本质是无法 cast

        // and: 其实 List<?> === List<? extend Object>
        f2(list);
        List<?> f2Arg = list;

        // and:
        f3(list);
        List<? extends Object> f3Arg = list;

        // and:
        // f4(list);
        // List<? super Object> f4Arg = list;

    }

}
