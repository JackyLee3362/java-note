package edu.note.java.stream.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jackylee
 * @date 2025-11-28 10:41
 */
public class StreamMapTest {
  @Test
  @DisplayName("测试 实体类中提取 Map 是否为 null")
  void test01() {
    // given: 用户1
    Map<String, String> m1 = new HashMap<>();
    m1.put("foo", "foo-value");
    User u1 = new User(m1);

    // and: 用户2
    Map<String, String> m2 = new HashMap<>();
    m2.put("foo", "bar-value");
    User u2 = new User(m2);

    // and: 用户3
    Map<String, String> m3 = new HashMap<>();
    User u3 = new User(m3);

    // and: 用户4
    User u4 = new User(null);

    List<User> users = new ArrayList<>();
    users.add(u1);
    users.add(u2);
    users.add(u3);
    users.add(u4);

    // when:
    users.stream()
        .map(User::getInfo)
        .map(m -> m.get("foo"))
        .forEach(System.out::println);

    // then:

  }

  @AllArgsConstructor
  @Data
  static private class User {
    Map<String, String> info;
  }
}
