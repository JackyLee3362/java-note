package edu.note.spring.bean.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

/**
 * 坑1: Spring BeanUtils.copyProperties 只能复制同名同类型的属性，不能进行类型转换。
 * 坑2: 对于集合类型的属性，Spring BeanUtils.copyProperties 只会复制引用，而不会进行深拷贝。
 * 坑3: 源对象包装类，标对象基本数据，BeanUtils.copyProperties 也无法复制。
 * ... 太多坑了，不用
 * 
 * @author jackylee
 * @date 2025-12-08 16:02
 */
public class BeanUtilTest {

    @Test
    @DisplayName("测试 bean 属性复制")
    void test01() {
        // given:
        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        Source source = Source.builder()
                .id(1L)
                .name("Foo")
                .age(18)
                .address(Arrays.asList("Address1", "Address2"))
                .info(map)
                .build();
        Target target = new Target();

        // when:
        BeanUtils.copyProperties(source, target);

        // then:
        assertNull(target.getId()); // Source.id 是 Long 类型，Target.id 是 String 类型，无法复制
        assertEquals(target.getName(), "Foo");
        assertEquals(target.getAge(), 18);
        assertEquals(target.getAddress().get(0), "Address1");
        assertEquals(target.getAddress().get(1), "Address2");
        assertEquals(target.getInfo().get("k1"), "v1");
        assertEquals(target.getInfo().get("k2"), "v2");
    }

}
