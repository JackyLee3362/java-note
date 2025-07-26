package edu.note.spring.starter.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("demo.user.teacher")
public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    private String name;
    private Integer age;
    private Double height;
    private Double weight;

}
