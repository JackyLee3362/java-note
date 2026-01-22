package edu.note.spring.starter.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("demo.bmi.user")
public class User {

    private String name;

    private Integer age;

    private Double height;

    private Double weight;

}
