package edu.note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author jackylee
 * @date 2026-01-19 18:53
 */
@SpringBootApplication
public class StartApp implements ApplicationListener<ContextRefreshedEvent> {
    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Context Refresh Event...");
    }

}
