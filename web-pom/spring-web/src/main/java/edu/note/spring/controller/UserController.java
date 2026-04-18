package edu.note.spring.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.note.spring.model.Address;
import edu.note.spring.model.User;

/**
 * @author jackylee
 * @date 2026-04-18 19:22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/model")
    public Map<String, Object> testModel(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return map;
    }

    @GetMapping("/modelAttribute/{name}/{age}")
    public Map<String, Object> testModelAttribute(@ModelAttribute User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return map;
    }

    @GetMapping("/requestBody/list")
    public Map<String, Object> testRequestBodyWithList(@RequestBody List<String> list) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        return map;
    }

    /**
     * @see HttpMessageConverter
     * @see MappingJackson2HttpMessageConverter 是实际的 Converter
     * @param user
     * @return
     */
    @PostMapping("/insert")
    public User insert(@RequestBody User user) {
        System.out.println("insert User=" + user);
        return user;
    }

    @PostMapping("/date")
    // 默认使用 yyyy-MM-dd 的格式，但是是在哪里转换的呢? 使用 Converter 转换
    public String date(Date startDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return startDate.toString() + "," + endDate.toString();
    }

    @GetMapping("/address")
    // POJO 注入测试
    public String address(Address address) {
        System.out.println("address=" + address);
        return address.toString();
    }

    @GetMapping("/user-address")
    // 嵌套 POJO 注入测试
    public String user(User user) {
        System.out.println("user=" + user);
        return user.toString();
    }
}
