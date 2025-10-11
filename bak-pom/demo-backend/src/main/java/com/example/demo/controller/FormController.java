package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
public class FormController {
    @GetMapping("/form_layouts")
    public String form_layout(){
        return "/form/form_layouts";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("single-image") MultipartFile headerimg,
                         @RequestPart("photos") MultipartFile[] photos
                         // 也可以写成 List<MultipartFile>
                         ) throws IOException {
        log.info("上传的信息：email={},username={},image={},photos={}",
                email,username,headerimg.getSize(),photos.length);
        // 先判断是否为空
        if(!headerimg.isEmpty()){
           // 存到目标服务器
            String originalFilename = headerimg.getOriginalFilename();
           headerimg.transferTo(new File("D:\\"+originalFilename));
        }
        if(photos.length > 0){
            for(MultipartFile photo : photos){
                if(!photo.isEmpty())
                    photo.transferTo(new File("D:\\"+photo.getOriginalFilename()));
                // 在配置文件中加入下面的配置可以限制文件大小
//  spring.servlet.multipart.max-file-size = 10MB
//  spring.servlet.multipart.max-request-size = 100MB
            }
        }
        return "main";
    }
}
