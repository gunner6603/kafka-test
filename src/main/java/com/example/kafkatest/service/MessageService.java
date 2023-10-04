package com.example.kafkatest.service;

import com.example.kafkatest.config.message.OrderCreateMessage;
import com.example.kafkatest.infra.KafkaProducer;
import com.example.kafkatest.service.event.OrderCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private static final String TEST_TOPIC = "kafka-test";

    private final KafkaProducer kafkaProducer;

    public void sendTestMessage(String message) {
        kafkaProducer.sendMessage(TEST_TOPIC, message);
    }

    @TransactionalEventListener //동일 스레드에서 실행
    public void send(OrderCreateEvent orderCreateEvent) {
        log.info("send kafka message for order create event");
        OrderCreateMessage message = OrderCreateMessage.builder()
                .orderId(orderCreateEvent.getOrderId())
                .memberEmail(orderCreateEvent.getMemberEmail())
                .build();
        kafkaProducer.sendMessage(message);
    }
}
