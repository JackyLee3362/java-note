package edu.note.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rest")
public class TestRestController {
    
    //原始方式(了解即可，实际不会使用)
    @RequestMapping("/httpServletRequest")
    public String simpleParam(HttpServletRequest request) {
        // http://localhost:8080/httpServletRequest?name=Tom&age=10
        // 请求参数： name=Tom&age=10   （有2个请求参数）
        // 第1个请求参数： name=Tom   参数名:name，参数值:Tom
        // 第2个请求参数： age=10     参数名:age , 参数值:10

        String name = request.getParameter("name");//name就是请求参数名
        String ageStr = request.getParameter("age");//age就是请求参数名

        int age = Integer.parseInt(ageStr);//需要手动进行类型转换
        System.out.println(name + "  :  " + age);
        return "OK";
    }
    

    @GetMapping("/path/{id}/{name}")
    public Map<String, Object> testPathVariable(
            @PathVariable("id") Integer userId,
            @PathVariable("name") String userName,
            @PathVariable Map<String, String> info) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", userId);
        map.put("name", userName);
        map.put("info", info);
        return map;
    }

    @GetMapping("/param")
    public Map<String, Object> testRequestParam(
            @RequestParam("id") Integer userId, @RequestParam("names") List<String> userNames,
            @RequestParam(name = "extend", required = false) Map<String, String> info) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", userId);
        map.put("names", userNames);
        map.put("extend", info);
        return map;
    }

    @GetMapping("/requestBody")
    public Map<String, Object> testRequestBody(@RequestBody String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }

    @GetMapping("/requestHeader")
    public Map<String, Object> testRequestHeader(
            @RequestHeader("User-Agent") String userAgent,
            @RequestHeader Map<String, String> header) {
        Map<String, Object> map = new HashMap<>();
        map.put("user-Agent", userAgent);
        map.put("head", header);
        return map;
    }

    @GetMapping("/cookie")
    public Map<String, Object> testCookie(@CookieValue("_ga") String ga) {
        Map<String, Object> map = new HashMap<>();
        map.put("cookie_ga", ga);
        return map;
    }

    @PostMapping("/post")
    public Map<String, Object> testPost(@RequestBody String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }

    @GetMapping("/matrix")
    public Map<String, Object> testMatrixVariable(@MatrixVariable("price") Integer price,
            @MatrixVariable("brand") List<String> brand) {
        Map<String, Object> map = new HashMap<>();
        map.put("price", price);
        map.put("brand", brand);
        return map;
    }

    @PostMapping("/requestPart")
    public String postMethodName(@RequestPart("single-image") MultipartFile headerimg,
            @RequestPart("photos") MultipartFile[] photos) {
        // 先判断是否为空
        if (!headerimg.isEmpty()) {
            // 存到目标服务器
            String originalFilename = headerimg.getOriginalFilename();
            // headerimg.transferTo(new File("D:\\" + originalFilename));
        }
        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()) {
                    // photo.transferTo(new File("D:\\" + photo.getOriginalFilename()));
                }
                // 在配置文件中加入下面的配置可以限制文件大小
                // spring.servlet.multipart.max-file-size = 10MB
                // spring.servlet.multipart.max-request-size = 100MB
            }
        }
        return "done";
    }

}
