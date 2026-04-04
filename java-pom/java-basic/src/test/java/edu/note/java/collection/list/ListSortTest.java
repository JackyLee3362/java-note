package edu.note.java.collection.list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.java.collection.list.ListSortTest.User;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jackylee
 * @date 2026-04-03 11:23
 */
public class ListSortTest {

    @Data
    @AllArgsConstructor
    public static class User {
        private String name;
        private int age;
        private int weight;
    }

    @Test
    @DisplayName("测试sort")
    void test01() {
        User user1 = new User("user1", 20, 180);
        User user2 = new User("user2", 18, 160);
        User user3 = new User("user3", 20, 170);
        List<User> list = Arrays.asList(user1, user2, user3);

        // 1.按照年龄升序排序
        // [user2, user1, user3]
        list.sort(Comparator.comparing(User::getAge));
        Assertions.assertEquals(user2, list.get(0));

        // 2.按照年龄降序排序
        // [user1, user3, user2]
        list.sort(Comparator.comparing(User::getAge).reversed());
        Assertions.assertEquals(user1, list.get(0));

        // 3.按照体重升序排序
        // [user2, user3, user1]
        list.sort(Comparator.comparing(User::getWeight));
        Assertions.assertEquals(user2, list.get(0));

        // 4. 先按照年龄升序排序，如果年龄相同，则按照体重降序排序
        // [user2, user1, user3]
        list.sort(Comparator.comparing(User::getAge).thenComparing(Comparator.comparing(User::getWeight).reversed()));
        Assertions.assertEquals(user1, list.get(1));
    }

}
