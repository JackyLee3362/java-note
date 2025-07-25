package edu.note.spring.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.note.spring.aop.anno.MyAnno;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get")
    public String getById(int id) {
        return "user" + id;
    }

    @MyAnno
    @GetMapping("/list")
    public String list() {
        return "many users";
    }
}
