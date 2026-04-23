package edu.note.jackson.unserialize;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jackson 反序列化 - 进阶
 *
 * @author jackylee
 * @date 2025-10-14 17:21
 */
public class IT02_Advance_UnserializeTest {

    static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setup() {
        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
    }

    @Test
    @DisplayName("字符串(含列表) -> Java 对象")
    void testStringWithList() throws IOException {
        // given:
        String string = "{'name':'Mike', 'list':['hello','world'], 'map':null, 'set':null}";
        // and:
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");

        // when:
        Student student = objectMapper.readValue(string, Student.class);

        // then:
        assertEquals(student.getList(), list);
    }

    @Test
    @DisplayName("字符串(含Map) -> Java 对象")
    void testStringWithMap() throws IOException {
        // given:
        String string = "{'name':'Mike', 'list':null, 'map':{'hello':'world', 'foo':'bar'}, 'set':null}";
        // and:
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("hello", "world");
        map.put("foo", "bar");

        // when:
        Student student = objectMapper.readValue(string, Student.class);

        // then:
        assertEquals(student.getMap(), map);
    }

    @Test
    @DisplayName("字符串(含Map) -> Java 对象")
    void testStringWithSet() throws IOException {
        // given:
        String string = "{'name':'Mike', 'list':null, 'map':null, 'set':[1,2,2]}";
        // and:
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);

        // when:
        Student student = objectMapper.readValue(string, Student.class);

        // then:
        assertEquals(student.getSet(), set);
    }

    @Test
    @DisplayName("字符串(含对象) -> Java 对象")
    void testStringWithObject() throws IOException {
        // given:
        String string = "{'name':'mac', 'users': [{'name':'Mike', 'age':18}, {'name':'Foo', 'age':null}]}";
        // and:
        User u1 = new User("Mike", 18);
        User u2 = new User("Foo", null);
        ArrayList<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);

        // when:
        Platform platform = objectMapper.readValue(string, Platform.class);

        // then:
        assertEquals(platform.getName(), "mac");
        assertEquals(platform.getUsers(), users);
    }

    @Test
    void testJsonDemo7() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        String teacherJson = "{'name':'Mike', 'info':[{'key1':'hello'}, {'key1':'world'}]}";
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        Platform teacher = objectMapper.readValue(teacherJson, Platform.class);
        System.out.println(teacher);
    }

    @Test
    void testJsonDemo8() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String teacherJson = "{'name':'Mike', 'age':18}";
        Platform teacher = objectMapper.readValue(teacherJson, Platform.class);
        System.out.println(teacher);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static private class Student {
        private String name;
        private List<String> list;
        private Map<String, String> map;
        private Set<Integer> set;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static private class Platform {
        private String name;
        private List<User> users;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        private String name;
        private Integer age;
    }
}
