package edu.note.java.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 包装类测试
 * 
 * @author jackylee
 * @date 2025-12-25 15:08
 */
public class WrapperClassTest {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Animal {
        String name;
        Integer age;
        int weight;
        boolean canFly;
    }

    @Test
    @DisplayName("测试")
    void test01() {
        // given:
        Animal animal = Animal.builder()
                .name("dog")
                .build();

        assertEquals(0, animal.getWeight());
        assertEquals(null, animal.getAge());
        assertEquals(false, animal.isCanFly());

        // 三元运算符自动拆箱示例
        // Boolean a = null;
        // Integer num = a ? 1 : 2;
        // System.out.println(num);

    }

}
