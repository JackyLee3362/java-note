package edu.note.statemachine;

/**
 * @author jackylee
 * @date 2026-04-23 00:51
 */
public enum OrderEvent {
    SUBMIT, // 提交订单：INIT → WAIT_PAY
    PAY, // 支付：WAIT_PAY → PAID
    CONFIRM, // 确认收货：PAID → COMPLETED
    CANCEL, // 取消订单：任意状态 → CANCELED
    ;
}
