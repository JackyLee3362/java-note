package edu.note.java.stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jackylee
 * @date 2026-01-05 16:40
 */
public class StreamFilterTest {
    @Test
    @DisplayName("测试 filter")
    void test01() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> list2 = list.stream().filter(n -> n >= 2).collect(Collectors.toList());
        assertEquals(2, list2.size());
    }

    @Data
    @AllArgsConstructor
    public static class User {
        private String name;
        private int age;
        private LocalDate birthday;
    }

    @Test
    @DisplayName("测试")
    void test02() {
        LocalDate date = LocalDate.of(2000, 1, 1);
        User user1 = new User("user1", 20, date);
        User user2 = new User("user2", 18, LocalDate.of(2002, 1, 1));
        User user3 = new User("user3", 25, null);
        List<User> list = Arrays.asList(user1, user2, user3);
        List<User> filter = list.stream().filter(
                user -> Objects.nonNull(user.getBirthday())
                        && (user.getBirthday().isAfter(date)) || user.getBirthday().equals(date))
                .collect(Collectors.toList());
        Assertions.assertEquals(user2, filter.get(0));
    }
}
