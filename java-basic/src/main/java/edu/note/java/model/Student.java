package edu.note.java.model;

import javax.annotation.PostConstruct;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025/7/15 14:44
 */
@NoArgsConstructor
public class Student {

    private String name;

    @PostConstruct
    public void init() {
        this.name = "default";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
