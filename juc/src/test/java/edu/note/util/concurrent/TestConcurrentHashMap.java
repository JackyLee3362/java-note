package edu.note.util.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/7/26 15:31
 */
public class TestConcurrentHashMap {
    @Test
    void test() {
        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>(16, 0.75f, 16);
        System.out.println(objectObjectConcurrentHashMap);
    }

}
