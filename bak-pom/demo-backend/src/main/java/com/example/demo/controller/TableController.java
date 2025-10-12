package com.example.demo.controller;

import com.example.demo.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class TableController {

    @GetMapping("dynamic_table")
    public String advanced_table(Model model) {
        List<User> users = Arrays.asList(new User("jacky", "123"),
                new User("lijie", "123456"),
                new User("parker", "xth"),
                new User("yzy", "zdyzy"));
        model.addAttribute("users", users);
        return "table/dynamic_table";
    }

}
