package com.jacky.boot.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RESTController extends gotoController {
    @GetMapping("/cars/sell")
    public Map matrixTest(
            @MatrixVariable("price") Integer price,
            @MatrixVariable("brand") List<String> brand) {
        Map<String, Object> map = new HashMap<>();
        map.put("price", price);
        map.put("brand", brand);
        return map;
    }

    @GetMapping("/user/{id}/car/{name}")
    public Map<String, Object> getStr(
            @PathVariable("id") Integer userId,
            @PathVariable("name") String carName,
            @PathVariable Map<String, String> pv,
            @RequestHeader("User-Agent") String userAgent,
            @RequestHeader Map<String, String> header,
            @RequestParam("age") Integer age,
            @RequestParam("interest") List<String> interest,
            @RequestParam Map<String, String> paramOfAll
    // @CookieValue("_ga") String ga
    ) {

        Map<String, Object> map = new HashMap<>();
        // map.put("id", userId);
        // map.put("name", carName);
        // map.put("pv", pv);
        map.put("user-Agent", userAgent);
        // map.put("head",header);
        map.put("age", age);
        map.put("interest", interest);
        map.put("param of all", paramOfAll);
        // map.put("cookie_ga",ga);
        return map;
    }

    @PostMapping("/save")
    public Map<String, String> postTest(
            @RequestBody String content) {
        Map<String, String> map = new HashMap<>();
        map.put("content", content);
        return map;
    }

    // @RequestMapping("/user", method = RequestMethod.GET)
    @GetMapping("/user")
    public String getUser() {
        return "get method";
    }

    @PostMapping("/user")
    public String getPost() {
        return "post method";
    }

    @DeleteMapping("/user")
    public String deletePost() {
        return "delete method";
    }

    @PutMapping("/user")
    public String putPost() {
        return "put method";
    }
}
