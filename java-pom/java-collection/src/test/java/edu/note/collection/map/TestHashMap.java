package edu.note.collection.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class TestHashMap {
    @Test
    @DisplayName("测试Map的基本操作")
    void testBasic() {

        // 创建
        HashMap<Integer/* id */, String/* 省份 */> map = new HashMap<>();
        String string = map.get(1);
        Assertions.assertNull(string);
        // 添加
        map.put(1, "浙江");
        map.put(2, "江苏");
        map.put(3, "上海");
        // 删除
        map.remove(1);
        // 获取
        map.get(2);
        // 清空
        map.clear();
        // 为空
        map.isEmpty();
        // 判断包含键
        map.containsKey(1);
        // 判断包含值
        map.containsValue("浙江");
        // 长度
        map.size();

    }

    @Test
    @DisplayName("测试 null 传入构造器")
    void testNullToConstructor() {

        HashMap<String, String> map = new HashMap<>(null);
        Assertions.assertEquals(0, map.size());

    }

    @Test
    @DisplayName("测试Map的迭代器")
    void testIterator() {
        HashMap<Integer/* id */, String/* 省份 */> map = new HashMap<>();

        map.put(1, "江苏");
        map.put(2, "浙江");
        map.put(3, "福建");
        map.put(4, "山东");

        // 按照 key 遍历
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            String val = map.get(key);
        }

        // 按照 entry 遍历
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            Integer key = entry.getKey();
            String val = entry.getValue();
        }

        // lambda 遍历
        map.forEach((id, s) -> {
            // ...
        });
        map.forEach((Integer id, String s) -> {
            // ...
        });
        map.forEach(new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer key, String value) {
                // ...
            }
        });

        // Stream 遍历
        map.entrySet().stream().filter(null).forEach(null);

    }
}
