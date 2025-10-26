package edu.note.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jackylee
 * @date 2025-10-26 22:56
 */
@RestController
@RequestMapping("/foo")
public class FooController {

    @GetMapping("/bar")
    public String foo() {
        return "should be intercept...";
    }

}
