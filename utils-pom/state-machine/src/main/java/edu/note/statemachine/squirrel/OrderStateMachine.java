package edu.note.statemachine.squirrel;

import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

import edu.note.statemachine.OrderEvent;
import edu.note.statemachine.OrderState;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2026-04-23 23:08
 */
@StateMachineParameters(stateType = OrderState.class, eventType = OrderEvent.class, contextType = String.class)
@Slf4j
public class OrderStateMachine extends AbstractUntypedStateMachine {
    // 提交订单：INIT → WAIT_PAY
    protected void submit(OrderState from, OrderState to, OrderEvent event, String ctx) {
        log.info("✅ 订单提交：{}", ctx);
    }

    // 支付：WAIT_PAY → WAIT_SEND
    protected void pay(OrderState from, OrderState to, OrderEvent event, String ctx) {
        log.info("💳 订单支付：{}", ctx);
    }

    // 发货：WAIT_SEND → COMPLETE
    protected void send(OrderState from, OrderState to, OrderEvent event, String ctx) {
        log.info("📦 订单完成：{}", ctx);
    }

    // 取消：任意状态 → CANCELED
    protected void cancel(OrderState from, OrderState to, OrderEvent event, String ctx) {
        log.info("❌ 订单取消：{}，原状态是 {}", ctx, from);
    }
}
