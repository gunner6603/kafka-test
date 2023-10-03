package com.example.kafkatest.service.event;

import com.example.kafkatest.domain.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderCreateEvent {

    private Long orderId;
    private String productName;

    public static OrderCreateEvent from(Order order) {
        return new OrderCreateEvent(order.getId(), order.getProductName());
    }
}
