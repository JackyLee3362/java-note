package edu.note.spring.template;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @RequestMapping("/hello")
    public String hello(@RequestParam long id) {
        return "你好，你的id是 " + id;
    }
}
