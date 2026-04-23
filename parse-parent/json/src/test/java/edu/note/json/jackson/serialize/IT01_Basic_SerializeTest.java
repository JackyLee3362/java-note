package edu.note.jackson.serialize;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jackson 基础序列化测试
 *
 * @author jackylee
 * @date 2025/7/1 17:28
 */
public class IT01_Basic_SerializeTest {

    @Test
    @DisplayName("对象 -> 字符串")
    void test1() throws JsonProcessingException {
        // given:
        final ObjectMapper objectMapper = new ObjectMapper();
        // and:
        User u1 = new User("Mike", 18);
        User u2 = new User("Mike", null);
        User u3 = new User(null, null);

        // when:
        String s1 = objectMapper.writeValueAsString(u1);
        String s2 = objectMapper.writeValueAsString(u2);
        String s3 = objectMapper.writeValueAsString(u3);

        // then:
        assertEquals(s1, "{\"name\":\"Mike\",\"age\":18}");
        assertEquals(s2, "{\"name\":\"Mike\",\"age\":null}");
        assertEquals(s3, "{\"name\":null,\"age\":null}");

    }

    @Test
    @DisplayName("对象(包含时间) -> 字符串")
    void test2() throws JsonProcessingException {
        // given:
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        // and:
        LocalDateTime dt = LocalDateTime.of(2008, 1, 2, 3, 4, 5);
        // and:
        Student s1 = Student.builder()
                .name("Mike")
                .date(dt.toLocalDate())
                .time(dt.toLocalTime())
                .datetime(dt)
                .build();
        Student s2 = Student.builder()
                .name("Mike")
                .datetime(dt)
                .build();

        // when:
        String st1 = objectMapper.writeValueAsString(s1);
        String st2 = objectMapper.writeValueAsString(s2);

        // then:
        assertEquals(st1,
                "{\"name\":\"Mike\",\"date\":\"2008-01-02\",\"time\":\"03:04:05\",\"datetime\":\"2008-01-02 03:04:05\"}");
        assertEquals(st2,
                "{\"name\":\"Mike\",\"date\":null,\"time\":null,\"datetime\":\"2008-01-02 03:04:05\"}");
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static private class User {
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