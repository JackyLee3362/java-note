package edu.note.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    // 该请求会请求到 success 这个请求上，success 请求会拿到 msg 这个属性
    // 客户端请求无法设置 msg 这个属性，只有服务器端请求才能设置这个属性
    @GetMapping("/goto")
    public String gotoPage(HttpServletRequest request) {
        request.setAttribute("msg", "good job!");
        request.setAttribute("name", "jackylee");
        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map<?, ?> receiveGoto(
            @RequestAttribute("msg") String msg) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("msg", msg);
        return map;
    }

    @GetMapping("thymeleaf")
    public String got(Model model) {
        model.addAttribute("msg", "hello, thymeleaf!");
        // model.addAttribute("web","http://www.jackylee.cn");
        return "page";
    }
}
