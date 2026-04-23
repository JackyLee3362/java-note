package edu.note.statemachine.cola;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.StateMachineFactory;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;

import edu.note.statemachine.OrderContext;
import edu.note.statemachine.OrderEvent;
import edu.note.statemachine.OrderState;

/**
 * @author jackylee
 * @date 2026-04-24 00:13
 */
public class ColaStateMachineTest {
    private static final String MACHINE_ID = "ORDER_STATE_MACHINE";

    @Test
    @DisplayName("测试 Alibaba Cola 状态机的基本使用")
    void test_basic_01() {

        // ========== 1. 构建状态机 ==========
        StateMachineBuilder<OrderState, OrderEvent, OrderContext> builder = StateMachineBuilderFactory.create();

        // 正常流程
        builder.externalTransition()
                .from(OrderState.INIT)
                .to(OrderState.WAIT_PAY)
                .on(OrderEvent.SUBMIT)
                .when((ctx) -> true) // 条件判断（可选）
                .perform((from, to, event, ctx) -> System.out.println("✅ 提交订单：" + ctx.getOrderId()));

        builder.externalTransition()
                .from(OrderState.WAIT_PAY)
                .to(OrderState.PAID)
                .on(OrderEvent.PAY)
                .perform((from, to, event, ctx) -> System.out.println("💳 支付完成：" + ctx.getOrderId()));

        builder.externalTransition()
                .from(OrderState.PAID)
                .to(OrderState.COMPLETED)
                .on(OrderEvent.CONFIRM)
                .perform((from, to, event, ctx) -> System.out.println("📦 已发货：" + ctx.getOrderId()));

        // ========== 重点：Cola 支持 fromAny()！！！ ==========
        for (OrderState state : OrderState.values()) {
            if (state != OrderState.CANCELED) { // 排除目标状态自身
                builder.externalTransition()
                        .from(state)
                        .to(OrderState.CANCELED)
                        .on(OrderEvent.CANCEL)
                        .perform((from, to, event, ctx) -> System.out
                                .println("❌ 订单取消：" + ctx.getOrderId() + " 来自状态：" + from));
            }
        }

        // 构建
        builder.build(MACHINE_ID);

        // ========== 2. 获取状态机 ==========
        StateMachine<OrderState, OrderEvent, OrderContext> stateMachine = StateMachineFactory.get(MACHINE_ID);

        // ========== 3. 测试：正常流程 ==========
        System.out.println("===== 正常流程 =====");
        OrderContext ctx = new OrderContext("ORDER_001");
        OrderState currentState = OrderState.INIT;

        currentState = stateMachine.fireEvent(currentState, OrderEvent.SUBMIT, ctx);
        currentState = stateMachine.fireEvent(currentState, OrderEvent.PAY, ctx);
        currentState = stateMachine.fireEvent(currentState, OrderEvent.CONFIRM, ctx);
        System.out.println("最终状态：" + currentState);

        // ========== 4. 测试：中途取消 ==========
        System.out.println("\n===== 中途取消 =====");
        currentState = OrderState.INIT;
        OrderContext ctx2 = new OrderContext("ORDER_002");

        currentState = stateMachine.fireEvent(currentState, OrderEvent.SUBMIT, ctx2);
        currentState = stateMachine.fireEvent(currentState, OrderEvent.CANCEL, ctx2);
        System.out.println("最终状态：" + currentState);
    }
}
