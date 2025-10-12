package edu.note.spring.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jackylee
 * @date 2025-10-11 19:58
 */
@RestController
public class UserRestController {

    @GetMapping("/user/list")
    public String getUserList() {
        return "mock users list";
    }

    @GetMapping("/user")
    public String getMethodName(@RequestParam String param) {
        return "Hello";
    }

    // @RequestMapping("/user", method = RequestMethod.GET)
    @GetMapping("/user")
    public String getUser() {
        return "get method";
    }

    @PostMapping("/user")
    public String insertUser() {
        return "post method";
    }

    @DeleteMapping("/user")
    public String deleteUser() {
        return "delete method";
    }

    @PutMapping("/user")
    public String updateUser() {
        return "put method";
    }

}
