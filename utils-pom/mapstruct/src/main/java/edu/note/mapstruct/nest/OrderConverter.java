package edu.note.mapstruct.nest;

import org.mapstruct.Mapping;

/**
 * @author jackylee
 * @date 2026-03-27 17:21
 */

public interface OrderConverter {

    @Mapping(source = "name", target = "userName")
    @Mapping(source = "age", target = "userAge")
    OrderPO to(User user);

    @Mapping(source = "id", target = "id")
    OrderPO to(Order order);

}
