package edu.note.spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jackylee
 * @date 2025-10-21 12:23
 */
@Controller
@RequestMapping(value = "/login", produces = "text/plain; charset=utf-8")
public class LoginController {

    // 需要配置 thymeleaf 才可以
    @ResponseBody
    @GetMapping({ "", "/" })
    public String login() throws Exception {
        return "please login first...";
    }

    @ResponseBody
    @RequestMapping("/register")
    public String register(@RequestParam("name") String username, String password) {
        System.out.println("name=" + username);
        System.out.println("password=" + password);
        return "registering..." + ":name=" + username + ",password=" + password;
    }

    @ResponseBody
    @GetMapping("/like")
    // 这里不使用 RequestParam 的话 List<String> 会被当成一个实体类
    public String like(@RequestParam List<String> like) {
        System.out.println("like=" + like);
        return like.toString();
    }

    @ResponseBody
    @PostMapping("/body")
    public String body(@RequestBody String body) {
        System.out.println("body=" + body);
        return body;
    }

    @ResponseBody
    @GetMapping("/path/{id}")
    public String path(@PathVariable Integer id) {
        return id.toString();
    }

    @ResponseBody
    @GetMapping("/header")
    public String header(
            @RequestHeader("Accept-Encoding") String encoding) {
        // @RequestHeader("Keep-Alive") Long keepAlive
        return "Accept-Encoding=" + encoding;
    }

    @ResponseBody
    @GetMapping("/matrix/{userId}")
    // 貌似不起作用
    public String matrix(
            // https://www.cnblogs.com/yoshi/p/14373866.html
            @PathVariable Integer userId,
            // @MatrixVariable String name,
            @MatrixVariable Integer age) {
        // return "hello" + ",userId=" + userId.toString() + ",name=" + name + ",age=" +
        // age.toString();
        return userId.toString() + age.toString();
    }

}
