package io.note.spring.starter.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
@ConfigurationProperties("demo.user.student")
public class Student {
    private String name;
    private String age;
    private String weights;
    private String height;

}
