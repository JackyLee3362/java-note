package edu.note.java.collection.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import edu.note.java.collection.map.WordCount;

@Slf4j
public class WordCountTest {

    @Test
    void testDemo01() {
        WordCount.demo(
            // 创建 map 集合
            // 创建 ConcurrentHashMap 对不对？
            () -> new ConcurrentHashMap<String, LongAdder>(8, 0.75f, 8),

            (map, words) -> {
                for (String word : words) {

                    // 如果缺少一个 key，则计算生成一个 value , 然后将 key value 放入 map
                    // a 0
                    LongAdder value = map.computeIfAbsent(word, (key) -> new LongAdder());
                    // 执行累加
                    value.increment(); // 2

                    /*
                     * // 检查 key 有没有
                     * Integer counter = map.get(word);
                     * int newValue = counter == null ? 1 : counter + 1;
                     * // 没有 则 put
                     * map.put(word, newValue);
                     */
                }
            });
    }
}
