package edu.note.java.delegate;

import lombok.Data;

@Data
public class StarImpl implements Star {

    private String name;

    public StarImpl(String name) {
        this.name = name;
    }

    @Override
    public String sing(String name) {
        System.out.println(this.name + "正在唱" + name);
        return "唱毕，谢谢！";
    }

    // 跳舞
    @Override
    public void dance() {
        System.out.println(this.name + "正在跳舞");
    }

}
