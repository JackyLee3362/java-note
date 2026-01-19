package edu.note.jwt;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class JwtUtilTest {
    static String token;


    @Test
    void createJWT() {
        long expired = System.currentTimeMillis() + 15 * 60 * 1000;
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "123");
        claims.put("name", "alice");
        token = JwtUtil.createJWT("Jacky", expired, claims);
        System.out.println("加密后的结果是："+token);
    }

    @Test
    void parseJWT() {
        if(token == null){
            return;
        }
        Claims claims = JwtUtil.parseJWT("Jacky", token);
        System.out.println("解析后的结果是："+claims);
    }
}