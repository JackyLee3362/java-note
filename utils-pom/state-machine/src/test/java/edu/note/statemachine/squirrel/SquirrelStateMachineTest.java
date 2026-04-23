package edu.note.statemachine.squirrel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;

import edu.note.statemachine.OrderEvent;
import edu.note.statemachine.OrderState;

/**
 * @author jackylee
 * @date 2026-04-23 23:09
 */
public class SquirrelStateMachineTest {
    @Test
    @DisplayName("测试 squirrel 状态机的基本使用")
    void test_basic_0() {
        // given:

        // when:

        // then:

        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(OrderStateMachine.class);

        // 定义转换规则
        builder.externalTransition()
                .from(OrderState.INIT)
                .to(OrderState.WAIT_PAY)
                .on(OrderEvent.SUBMIT)
                .callMethod("submit");

        builder.externalTransition()
                .from(OrderState.WAIT_PAY)
                .to(OrderState.PAID)
                .on(OrderEvent.PAY)
                .callMethod("pay");

        builder.externalTransition()
                .from(OrderState.PAID)
                .to(OrderState.COMPLETED)
                .on(OrderEvent.CONFIRM)
                .callMethod("confirm");
        for (OrderState state : OrderState.values()) {
            if (state != OrderState.CANCELED) { // 排除目标状态自身
                builder.externalTransition()
                        .from(state).to(OrderState.CANCELED)
                        .on(OrderEvent.CANCEL).callMethod("cancel");
            }
        }

        // 初始状态：INIT
        UntypedStateMachine fsm = builder.newStateMachine(OrderState.INIT);
        // 模拟流程
        fsm.fire(OrderEvent.SUBMIT, "订单001");
        fsm.fire(OrderEvent.PAY, "订单001");
        fsm.fire(OrderEvent.CONFIRM, "订单001");
    }
}
