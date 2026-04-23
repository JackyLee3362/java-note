package edu.note.json.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jackylee
 * @date 2025-12-12 09:44
 */
public class GsonObjToJsonTest {

    @AllArgsConstructor
    @Data
    static class User {
        String name;
        int age;
    }

    @Test
    @DisplayName("测试 Gson: Object <-> json")
    void test01() {
        Gson gson = new Gson();

        User user = new User("Foo", 18);
        String json = gson.toJson(user);
        assertEquals("{\"name\":\"Foo\",\"age\":18}", json);

        User userFromJson = gson.fromJson(json, User.class);
        assertEquals(user, userFromJson);
    }

    @Test
    @DisplayName("测试")
    void test02() {
        // given:
        Gson gson = new Gson();
        String json = "{}";

        // when:
        Map<?, ?> fromJson = gson.fromJson(json, Map.class);

        // then:
        assertEquals(fromJson, new HashMap<>());

    }

}
