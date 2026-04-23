package edu.note.statemachine.spring;

import javax.annotation.Resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import edu.note.statemachine.OrderEvent;
import edu.note.statemachine.OrderState;
import reactor.core.publisher.Mono;

/**
 * @author jackylee
 * @date 2026-04-23 00:53
 */

@SpringBootApplication
@SpringBootTest
public class SpringStateMachineTest {

    @Resource
    private StateMachineFactory<OrderState, OrderEvent> factory;

    @Test
    @DisplayName("最简单的实践 - 老版写法")
    void testFactoryFlow() {
        // 按订单ID创建独立状态机实例（并发安全）
        StateMachine<OrderState, OrderEvent> sm = factory.getStateMachine("ORDER_001");
        sm.start(); // 3.2.1必须手动启动
        // 事件发送逻辑完全不变
        sm.sendEvent(MessageBuilder.withPayload(OrderEvent.PAY).build());
    }

    @Test
    @DisplayName("最简单的实践 - 响应式写法")
    void testFactoryFlowV2() {
        // 按订单ID创建独立状态机实例（并发安全）
        StateMachine<OrderState, OrderEvent> sm = factory.getStateMachine("ORDER_001");
        sm.startReactively().block(); // 3.2.1必须手动启动
        // 事件发送逻辑完全不变
        sm.sendEvent(Mono.just(MessageBuilder.withPayload(OrderEvent.PAY).build())).blockFirst();
    }
}
