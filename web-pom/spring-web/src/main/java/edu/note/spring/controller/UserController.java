package edu.note.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.note.spring.Address;
import edu.note.spring.User;

/**
 * @author jackylee
 * @date 2025-10-25 23:44
 */
@RestController
@RequestMapping("/user")
// 使用 EnableMvc 注解开启 json 转对象的功能
public class UserController {

    @PostMapping("/list")
    public String list(@RequestBody List<String> users) {
        System.out.println("insert User=" + users);
        return users.toString();
    }

    @PostMapping("/insert")

    /**
     * @see HttpMessageConverter
     * @see MappingJackson2HttpMessageConverter 是实际的 Converter
     * @param user
     * @return
     */
    public User insert(@RequestBody User user) {
        System.out.println("insert User=" + user);
        return user;
    }

    @PostMapping("/date")
    // 默认使用 yyyy-MM-dd 的格式，但是是在哪里转换的呢? 使用 Converter 转换
    public String date(Date startDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return startDate.toString() + "," + endDate.toString();
    }

    @ResponseBody
    @GetMapping("/address")
    public String address(Address address) {
        System.out.println("address=" + address);
        return address.toString();
    }

    @ResponseBody
    @GetMapping("/user-address")
    public String user(User user) {
        System.out.println("user=" + user);
        return user.toString();
    }

}
