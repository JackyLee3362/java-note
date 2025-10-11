package com.example.demo.controller;

import com.example.demo.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping(value={"/", "/login"})
    public String loginPage(){

        return "login";
    }
    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){
        if(StringUtils.hasLength(user.getUserName()) && user.getPassword().equals("123")){
            session.setAttribute("loginUser",user);
            return "redirect:/main.html";
        }
        model.addAttribute("msg","Username or password error");
        return "login";
    }

    @GetMapping("/main.html")
    public String mainPage(HttpSession session, Model model){
        // 使用拦截器之后，这段就可以注释了
//        if(session.getAttribute("loginUser") != null){
//            return "main";
//        }
//        model.addAttribute("msg", "please login first!");
//        return "login";
        return "main";
    }
}
