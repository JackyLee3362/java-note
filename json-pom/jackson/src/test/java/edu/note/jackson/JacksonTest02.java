package edu.note.jackson;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jackylee
 * @date 2025-10-14 17:21
 */
public class JacksonTest02 {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("测试 convertValue")
    void test01() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "alice");
        map.put("age", 12);
        User user = objectMapper.convertValue(map, User.class);
        assertEquals("alice", user.getName());
        assertEquals(12, user.getAge());
    }

    @Test
    @DisplayName("测试反序列化为 list")
    void testList() throws JsonProcessingException {
        List<User> list = new ArrayList<>();
        list.add(new User("alice", 12));
        list.add(new User("bob", 22));
        String jsonString = objectMapper.writeValueAsString(list);
        List<User> users = objectMapper.readValue(jsonString, new TypeReference<List<User>>() {
        });
        assertEquals("alice", users.get(0).getName());
        assertEquals(12, users.get(0).getAge());
    }

}
