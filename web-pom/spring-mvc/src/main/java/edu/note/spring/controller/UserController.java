package edu.note.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.note.spring.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author jackylee
 * @date 2025-10-25 23:44
 */
@RestController
@RequestMapping("/user")
// 使用 EnableMvc 注解开启 json 转对象的功能
public class UserController {

    @PostMapping("/insert")
    public User insert(@RequestBody User user) {
        System.out.println("insert User=" + user);
        return user;
    }

}
