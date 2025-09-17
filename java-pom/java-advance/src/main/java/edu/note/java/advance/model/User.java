package edu.note.java.advance.model;

import lombok.Data;

/**
 * @author jackylee
 * @date 2025/7/7 14:17
 */

@Data
public class User {

    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
