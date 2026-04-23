package edu.note.statemachine.spring;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import edu.note.statemachine.OrderEvent;
import edu.note.statemachine.OrderState;

/**
 * @author jackylee
 * @date 2026-04-23 00:51
 */
@Configuration
@EnableStateMachineFactory // 生产多实例首选
public class SimpleStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {
    // 1. 配置状态（初始+结束+所有状态）
    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderState.WAIT_PAY) // 初始状态
                .end(OrderState.COMPLETED) // 结束状态
                .states(EnumSet.allOf(OrderState.class)); // 加载所有状态
    }

    // 2. 配置流转规则（状态→事件→新状态）
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                // 待支付 → 支付事件 → 已支付
                .withExternal().source(OrderState.WAIT_PAY).target(OrderState.PAID).event(OrderEvent.PAY)
                .and()
                // 已支付 → 确认收货事件 → 已完成
                .withExternal().source(OrderState.PAID).target(OrderState.COMPLETED).event(OrderEvent.CONFIRM);
    }

    // 3. 极简监听器（仅打印状态变化）
    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderState, OrderEvent> config) throws Exception {
        config.withConfiguration()
                .listener(new StateMachineListenerAdapter<OrderState, OrderEvent>() {
                    // 监听状态流转
                    @Override
                    public void stateChanged(State<OrderState, OrderEvent> from, State<OrderState, OrderEvent> to) {
                        if (from == null) {
                            System.out.println("【状态机初始化】初始状态：" + to.getId());
                        } else {
                            System.out.println("【状态流转】" + from.getId() + " → " + to.getId());
                        }
                    }

                    // 监听非法事件（如未支付直接确认收货）
                    @Override
                    public void eventNotAccepted(Message<OrderEvent> event) {
                        System.err.println("【非法操作】当前状态不支持事件：" + event);
                    }
                });
    }
}
