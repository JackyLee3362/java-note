package edu.note.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jackylee
 * @date 2025-10-21 12:23
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping(value={"/"})
    public Boolean login(Long userId) throws Exception {
        throw new Exception("未实现异常");
    }

}
