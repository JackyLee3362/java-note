package edu.note.spring.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jackylee
 * @date 2025-10-22 12:30
 */
@RestController
public class LoginController {

    @GetMapping(value = { "/", "/login" })
    public String loginPage() {
        return "loginx";
    }

    @GetMapping("/user")
    public String user() {
        return "user page";
    }

}
