package edu.note.collection.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HashMapTest {

    @Test
    @DisplayName("HashMap 基础")
    void testBasic() {

        // 创建
        HashMap<Integer/* id */, String/* 省份 */> map = new HashMap<>();
        assertNull(map.get(1));
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

        System.out.println(map);

    }

    @Test
    @DisplayName("测试 null 传入构造器")
    void testNullToConstructor() {

        HashMap<String, String> map = new HashMap<>(null);
        assertEquals(0, map.size());

    }

    @Test
    @DisplayName("Map 遍历")
    void testIterator() {
        HashMap<Integer/* id */, String/* 省份 */> map = new HashMap<>();

        map.put(1, "江苏");
        map.put(2, "浙江");
        map.put(3, "福建");
        map.put(4, "山东");

        // 按照 key 遍历
        for (Integer key : map.keySet()) {
            String val = map.get(key);
        }

        // 按照 entry 遍历
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
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

        // 迭代器遍历
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            System.out.println(next + map.get(next));
        }

    }

    @Test
    @DisplayName("测试 Map.of")
    void testMapOf() {

        /*
         * 创建Map的不可变集合
         * 1. key 不能重复的
         * 2. Map.of 参数是有上限的，最多10个键值对
         * 3. 如果键值对大于10个，使用 Map.ofEntries
         */

        // 一旦创建完毕之后，是无法进行修改的，在下面的代码中，只能进行查询操作
        // Map<Integer, String> map = Map.of(10001, "南京", 10002, "北京", 10003, "上海")
    }

    @Test
    @DisplayName("Java9 复制")
    void testCopy() {

        /*
         * 创建Map的不可变集合,键值对的数量超过10个
         */

        // 1.创建一个普通的Map集合
        HashMap<String, String> hm = new HashMap<>();
        hm.put("张三", "南京");
        hm.put("李四", "北京");
        hm.put("王五", "上海");
        hm.put("赵六", "北京");
        hm.put("孙七", "深圳");
        hm.put("周八", "杭州");
        hm.put("吴九", "宁波");
        hm.put("郑十", "苏州");
        hm.put("刘一", "无锡");
        hm.put("陈二", "嘉兴");
        hm.put("aaa", "111");

        // 2.利用上面的数据来获取一个不可变的集合
        /*
         * //获取到所有的键值对对象（Entry对象）
         * Set<Map.Entry<String, String>> entries = hm.entrySet();
         * //把entries变成一个数组
         * Map.Entry[] arr1 = new Map.Entry[0];
         * //toArray方法在底层会比较集合的长度跟数组的长度两者的大小
         * //如果集合的长度 > 数组的长度 ：数据在数组中放不下，此时会根据实际数据的个数，重新创建数组
         * //如果集合的长度 <= 数组的长度：数据在数组中放的下，此时不会创建新的数组，而是直接用
         * Map.Entry[] arr2 = entries.toArray(arr1);
         * //不可变的map集合
         * Map map = Map.ofEntries(arr2);
         * map.put("bbb","222");
         */

        // Map<Object, Object> map = Map.ofEntries(hm.entrySet().toArray(new
        // Map.Entry[0]));

        // Map<String, String> map = Map.copyOf(hm);
        // map.put("bbb", "222");

    }
}
