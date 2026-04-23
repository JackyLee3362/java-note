package edu.note.statemachine;

/**
 * @author jackylee
 * @date 2026-04-23 00:51
 */
public enum OrderState {
    INIT, // 初始状态：待提交
    WAIT_PAY, // 待支付
    PAID, // 已支付
    COMPLETED, // 结束状态：已完成
    CANCELED, // 结束状态：已取消
    ;
}
