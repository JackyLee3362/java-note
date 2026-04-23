package edu.note.statemachine.spring;

import javax.annotation.Resource;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import edu.note.statemachine.OrderEvent;
import edu.note.statemachine.OrderState;
import reactor.core.publisher.Mono;

/**
 * @author jackylee
 * @date 2026-04-23 00:57
 */
public class StateMachineUtil {
    @Resource
    private StateMachineFactory<OrderState, OrderEvent> factory;

    // 创建状态机（无废弃）
    public StateMachine<OrderState, OrderEvent> create(String orderId) {
        StateMachine<OrderState, OrderEvent> sm = factory.getStateMachine(orderId);
        sm.startReactively().block();
        return sm;
    }

    // 发送事件（3.2.1 最终正确，无任何废弃）
    public void send(StateMachine<OrderState, OrderEvent> sm, OrderEvent event) {
        Message<OrderEvent> msg = MessageBuilder.withPayload(event).build();
        sm.sendEvent(Mono.just(msg)).blockFirst();
        // todo: blockFirst 和 blockLast 区别是什么
    }

}
