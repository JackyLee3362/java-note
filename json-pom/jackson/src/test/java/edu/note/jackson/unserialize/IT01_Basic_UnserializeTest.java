package edu.note.jackson.unserialize;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jackson 反序列化 - 基础
 *
 * @author jackylee
 * @date 2025/7/1 17:28
 */
public class IT01_Basic_UnserializeTest {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    // 类型转换接口 API readValue: 将 String 转换为 Object
    @Test
    @DisplayName("字符串 -> 对象")
    void testJsonDemo() throws IOException {
        // given: 准备数据
        String string = "{\"name\":\"Mike\", \"age\":18}";

        // when: 调用
        User user = objectMapper.readValue(string, User.class);

        // then: 断言
        assertEquals(user.getName(), "Mike");
        assertEquals(user.getAge(), 18);
    }

    // 类型转换接口 API readValue: 将 String 转换为 Object
    @Test
    @DisplayName("反序列化：从 json 获得 java 对象(包括时间对象)")
    void testJsonDemoWithTime() throws IOException {
        // given: 准备数据
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        LocalDateTime dt = LocalDateTime.of(2008, 1, 2, 3, 4, 5);
        String string = "{\"name\":\"Mike\",\"date\":\"2008-01-02\",\"time\":\"03:04:05\",\"datetime\":\"2008-01-02 03:04:05\"}";

        // when: 调用
        Student user = objectMapper.readValue(string, Student.class);

        // then: 断言
        assertEquals(user.getName(), "Mike");
        assertEquals(user.getDatetime(), dt);
        assertEquals(user.getDate(), dt.toLocalDate());
        assertEquals(user.getTime(), dt.toLocalTime());
    }

    // 类型转换接口 API readValue + TypeReference: 将 String 转换为 List
    @Test
    @DisplayName("测试反序列化为 list")
    void testList() throws JsonProcessingException {
        // given: 准备数据
        String string = "[{\"name\": \"alice\", \"age\": 12}, {\"name\": \"bob\", \"age\": 22}]";

        // when: 调用
        List<User> users = objectMapper.readValue(string, new TypeReference<List<User>>() {
        });

        // then: 断言
        assertEquals("alice", users.get(0).getName());
        assertEquals(12, users.get(0).getAge());
    }

    // 类型转换接口 API convertValue: 将 Map 转换为实体类
    @Test
    @DisplayName("测试 convertValue")
    void test02() {
        // given: 准备数据
        Map<String, Object> map = new HashMap<>();
        map.put("name", "alice");
        map.put("age", 12);

        // when: 调用
        User user = objectMapper.convertValue(map, User.class);

        // then: 断言
        assertEquals("alice", user.getName());
        assertEquals(12, user.getAge());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class User {
        private String name;
        private Integer age;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    static private class Student {
        private String name;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate date;

        @JsonFormat(pattern = "HH:mm:ss")
        private LocalTime time;

        // 设置序列化时的时间格式
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime datetime;
    }
}
