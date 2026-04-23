package edu.note.json.jackson.unserialize;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

/**
 * 如果存在外部类
 *
 * @author jackylee
 * @date 2026-02-03 18:17
 */
public class IT04_MixinTest {

    @Data
    static public class User {
        private String name;
        private Integer age;
        private ByteBuffer info;

        public User setByteBuffer(ByteBuffer info) {
            this.info = info;
            return this;
        }

        public User setByteBuffer(byte[] info) {
            if (info == null) {
                this.info = null;
                return this;
            } else {
                this.info = ByteBuffer.wrap(info);
            }
            return this;
        }

    }

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("测试")
    void test01() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Mike");
        User user = objectMapper.convertValue(map, User.class);
        System.out.println(user);
    }

}
