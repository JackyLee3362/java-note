package edu.note.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author jackylee
 * @date 2025-10-14 16:21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public List<User> getUserList() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "Foo", 12));
        list.add(new User(2, "Bar", 12));
        return list;
    }

    @GetMapping("/info")
    public User getUserById(@RequestParam Integer id) {
        return new User(id, "Foo", 12);
    }

    @PostMapping("/insert")
    public User insertUser(@RequestBody User user) {
        return user;
    }

    @DeleteMapping("/delete/{id}")
    public Boolean updateUser(@PathVariable Integer id) {
        return true;
    }
}
