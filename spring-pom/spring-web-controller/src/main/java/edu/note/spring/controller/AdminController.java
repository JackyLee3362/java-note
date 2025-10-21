package edu.note.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jackylee
 * @date 2025-10-21 23:41
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @ResponseBody
    @GetMapping("/heart")
    public String login(@RequestParam String param) {
        return "hello, admin";
    }

}
