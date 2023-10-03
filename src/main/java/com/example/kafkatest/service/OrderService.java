package com.example.kafkatest.service;

import com.example.kafkatest.domain.Order;
import com.example.kafkatest.repository.OrderRepository;
import com.example.kafkatest.service.event.OrderCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    public void createOrder(String productName) {
        final Order order = Order.builder()
                .productName(productName)
                .build();
        orderRepository.save(order);
        eventPublisher.publishEvent(OrderCreateEvent.from(order));
        log.info("published event in order_service");
    }
}
