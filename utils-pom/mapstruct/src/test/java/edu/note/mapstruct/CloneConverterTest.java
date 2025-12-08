package edu.note.mapstruct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.mapstruct.clone.CloneConverter;
import edu.note.mapstruct.clone.Order;
import edu.note.mapstruct.clone.User;

/**
 * @author jackylee
 * @date 2025-12-08 15:55
 */
public class CloneConverterTest {

    @Test
    @DisplayName("测试 Clone 自身")
    void test01() {
        Order order1 = new Order("order1", 100);
        Order order2 = new Order("order1", 100);
        User user1 = new User("Alice", Arrays.asList(order1, order2));
        User user2 = CloneConverter.INSTANCE.clone(user1);
        assertEquals(user1, user2); // 内容一致
        assertFalse(user1 == user2); // 地址不一致
        assertFalse(user1.getOrders().get(0) == user2.getOrders().get(0));
        assertFalse(user1.getOrders().get(1) == user2.getOrders().get(1));
    }

}
