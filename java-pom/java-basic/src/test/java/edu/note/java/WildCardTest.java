package edu.note.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025-10-09 21:53
 */
public class WildCardTest {

    static interface Food {
    }

    class Meat implements Food {
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Fruit implements Food {
        private String name;
    }

    class Apple extends Fruit {
        public Apple(String name) {
            super(name);
        }
    }

    class Banana extends Fruit {
        public Banana(String name) {
            super(name);
        }
    }

    @Test
    @DisplayName("测试编译")
    void test01() {

        int[] nums = { 1, 2, 3 };
        Arrays.stream(nums).forEach(System.out::println);
        Fruit f1 = new Fruit("f1");
        Fruit f2 = new Fruit("f2");
        Fruit[] fruits = new Fruit[] { f1, f2 };
        Arrays.stream(fruits).forEach(System.out::println);
        Fruit fruit = Arrays.stream(fruits)
                .findAny()
                .get();
        System.out.println(fruit);

    }

    @Test
    @DisplayName("测试")
    void test1() {
        List<Fruit> arrayList = new ArrayList<>();
        f1(arrayList);
        f2(arrayList);
    }

    public void f1(List<? extends Fruit> fruits) {
        Fruit fruit = fruits.get(0);
        // fruits.add(fruit);
    }

    public void f2(List<? super Fruit> fruits) {
        // Fruit fruit = fruits.get(0);
        fruits.add(new Fruit());
    }

}
