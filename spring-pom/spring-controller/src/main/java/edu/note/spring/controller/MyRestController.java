package edu.note.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class MyRestController {

    @GetMapping("/path/{id}/{name}")
    public Map<String, Object> testPathVariable(
            @PathVariable("id") Integer userId,
            @PathVariable("name") String carName,
            @PathVariable Map<String, String> pv) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", userId);
        map.put("name", carName);
        map.put("pv", pv);
        map.put("msg", "hello world");
        return map;
    }

    @GetMapping("/param")
    public Map<String, Object> testRequestParam(
            @RequestParam Integer age,
            @RequestParam List<String> interest,
            @RequestParam("extend") Map<String, String> info) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "hello world");
        map.put("age", age);
        map.put("interest", interest);
        map.put("param of all", info);
        return map;
    }

    public Map<String, Object> testRequestBody(@RequestBody String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }

    @GetMapping("/header")
    public Map<String, Object> testRequestHeader(
            @RequestHeader("User-Agent") String userAgent,
            @RequestHeader Map<String, String> header) {
        Map<String, Object> map = new HashMap<>();
        map.put("user-Agent", userAgent);
        map.put("head",header);
        return map;
    }

    @GetMapping("/cookie")
    public Map<String, Object> testCookie(@CookieValue("_ga") String ga) {
        Map<String, Object> map = new HashMap<>();
        map.put("cookie_ga",ga);
        return map;
    }

    @PostMapping("/post")
    public Map<String, Object> testPost(@RequestBody String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }

    @GetMapping("/matrix")
    public Map<String, Object> testMatrixVariable(@MatrixVariable("price") Integer price,
            @MatrixVariable("brand") List<String> brand) {
        Map<String, Object> map = new HashMap<>();
        map.put("price", price);
        map.put("brand", brand);
        return map;
    }
}
