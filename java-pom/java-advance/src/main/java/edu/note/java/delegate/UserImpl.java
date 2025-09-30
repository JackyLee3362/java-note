package edu.note.java.delegate;

import lombok.Data;

@Data
public class UserImpl implements User {

    private String name;

    public UserImpl(String name) {
        this.name = name;
    }

    @Override
    public String register(String name) {
        System.out.println(this.name + "is registering..." + name);
        return "Register Success!";
    }

    // 跳舞
    @Override
    public void browse() {
        System.out.println(this.name + "is browsing content...");
    }

}
