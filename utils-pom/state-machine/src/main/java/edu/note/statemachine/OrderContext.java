package edu.note.statemachine;

/**
 * @author jackylee
 * @date 2026-04-24 00:14
 */
public class OrderContext {
    private String orderId;

    public OrderContext(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
