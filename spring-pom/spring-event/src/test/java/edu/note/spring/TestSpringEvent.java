package edu.note.spring;

import javax.annotation.Resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-12-19 17:50
 */
@Slf4j
@SpringBootTest(classes = TestSpringEvent.class)
public class TestSpringEvent {

    @Resource
    ApplicationEventPublisher applicationEventPublisher;

    @Data
    @AllArgsConstructor
    public static class CustomEvent {
        private final String message;
    }

    @EventListener
    public void handleCustomEvent(CustomEvent event) {
        log.info("Received custom event - message: {}", event.getMessage());
    }

    @Test
    @DisplayName("测试事件 Event")
    void test01() {
        applicationEventPublisher.publishEvent(new CustomEvent("Hello, Spring Event!"));

    }

}
