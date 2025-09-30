package edu.note.spring;

/**
 * @author jackylee
 * @date 2025-09-30 14:35
 */
public class HelloServiceImplV2 implements HelloService {

    @Override
    public String hello(String name) {
        return "HELLO, " + name;
    }

    @Override
    public String bye() {
        return "BYE";
    }

}
