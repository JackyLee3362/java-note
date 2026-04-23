package edu.note.statemachine.stateless4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import com.github.oxo42.stateless4j.delegates.Trace;

import edu.note.statemachine.OrderEvent;
import edu.note.statemachine.OrderState;

/**
 * @author jackylee
 * @date 2026-04-23 23:59
 */
public class Stateless4jStateMachineTest {
        @Test
        @DisplayName("测试 stateless4j 状态机的基本使用")
        void test_basic_01() {
                // 3. 创建状态机配置
                StateMachineConfig<OrderState, OrderEvent> config = new StateMachineConfig<>();

                // 4. 配置每个状态的流转规则
                // 初始状态：提交→待付款；取消→已取消
                config.configure(OrderState.INIT)
                                .permit(OrderEvent.SUBMIT, OrderState.WAIT_PAY)
                                .permit(OrderEvent.CANCEL, OrderState.CANCELED);

                // 待付款：支付→待发货；取消→已取消
                config.configure(OrderState.WAIT_PAY)
                                .permit(OrderEvent.PAY, OrderState.PAID)
                                .permit(OrderEvent.CANCEL, OrderState.CANCELED)
                                .onEntry(() -> System.out.println("📥 进入待付款状态"))
                                .onExit(() -> System.out.println("📤 离开待付款状态"));

                // 待发货：发货→完成；取消→已取消
                config.configure(OrderState.PAID)
                                .permit(OrderEvent.CONFIRM, OrderState.COMPLETED)
                                .permit(OrderEvent.CANCEL, OrderState.CANCELED);

                // 完成/取消：无后续流转（忽略所有事件）
                for (OrderEvent orderEvent : OrderEvent.values()) {
                        config.configure(OrderState.COMPLETED).ignore(orderEvent);
                        config.configure(OrderState.CANCELED).ignore(orderEvent);
                }

                // 5. 创建状态机实例（直接用泛型，无类型转换坑！）
                StateMachine<OrderState, OrderEvent> fsm = new StateMachine<>(OrderState.INIT, config);

                // 6. 监听状态转换（可选，方便调试）
                fsm.setTrace(new Trace<OrderState, OrderEvent>() {
                        @Override
                        public void trigger(OrderEvent trigger) {
                                // 触发事件时回调
                        }

                        @Override
                        public void transition(OrderEvent trigger, OrderState source, OrderState dest) {
                                System.out.printf("🔄 %s → %s（事件：%s）%n", source, dest, trigger);
                        }
                });

                // 7. 测试正常流程
                System.out.println("--- 正常流程测试 ---");
                fsm.fire(OrderEvent.SUBMIT);
                fsm.fire(OrderEvent.PAY);
                fsm.fire(OrderEvent.CONFIRM);
                System.out.println("当前状态：" + fsm.getState());

                // 8. 测试中途取消
                System.out.println("\n--- 中途取消测试 ---");
                fsm = new StateMachine<>(OrderState.INIT, config); // 重置状态机
                fsm.fire(OrderEvent.SUBMIT);
                fsm.fire(OrderEvent.CANCEL);
                System.out.println("当前状态：" + fsm.getState());
        }

}
