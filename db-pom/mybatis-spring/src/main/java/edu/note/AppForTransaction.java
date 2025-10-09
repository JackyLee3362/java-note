package edu.note;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppForTransaction {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        // User user = ctx.getBean(User.class);
        // System.out.println(user);
        System.out.println("hhh");
    }
}
