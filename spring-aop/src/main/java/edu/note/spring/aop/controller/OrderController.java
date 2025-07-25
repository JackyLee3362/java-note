package edu.note.spring.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.note.spring.aop.anno.MyAnno;

@RestController
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/get")
    public String getById(int id) {
        return "order" + id;
    }

    @MyAnno
    @GetMapping("/list")
    public String list() {
        return "many order...";
    }

    @MyAnno
    @GetMapping("/delete")
    public String delete(int id) {
        return "order" + id;
    }
}
