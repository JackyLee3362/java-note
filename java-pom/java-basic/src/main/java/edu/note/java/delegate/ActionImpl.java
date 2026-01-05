package edu.note.java.delegate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionImpl implements IAction {

    private String userName;

    @Override
    public String register(String name) {
        System.out.println(this.userName + "is registering..." + name);
        return "Register Success!";
    }

    // 跳舞
    @Override
    public void browse() {
        System.out.println(this.userName + "is browsing content...");
    }

}
