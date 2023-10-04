package com.example.kafkatest.infra;

import com.example.kafkatest.config.message.OrderCreateMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {


    private final KafkaTemplate<String, String> defaultKafkaTemplate;
    private final KafkaTemplate<String, OrderCreateMessage> orderCreateKafkaTemplate;

    public void sendMessage(String topic, String message) {
        CompletableFuture<SendResult<String, String>> future = defaultKafkaTemplate.send(topic, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message=[{}] due to : [{}]", message, ex.getMessage());
            }
        });
    }

    public void sendMessage(OrderCreateMessage message) {
        CompletableFuture<SendResult<String, OrderCreateMessage>> future = orderCreateKafkaTemplate.send("order-create", message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message=[{}] due to : [{}]", message, ex.getMessage());
            }
        });
    }
}
