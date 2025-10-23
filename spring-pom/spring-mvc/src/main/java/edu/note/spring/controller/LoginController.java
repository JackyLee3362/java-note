package edu.note.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jackylee
 * @date 2025-10-21 12:23
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping(value = { "/" })
    public String login(Long userId) throws Exception {
        return "login/loginPage";
    }

}
