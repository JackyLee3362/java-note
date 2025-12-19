package edu.note;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
