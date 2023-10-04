package com.example.kafkatest.service;

import com.example.kafkatest.config.message.OrderCreateMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

    @KafkaListener(topics = "order-create", containerFactory = "orderCreateKafkaListenerContainerFactory")
    public void sendMail(@Payload OrderCreateMessage message,
                         @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) throws InterruptedException {
        log.info("Received Message: {} from partition: {}", message, partition);
        log.info("attempt to send email for {}", message.getMemberEmail());
        Thread.sleep(1000);
        log.info("completed sending email for {}", message.getMemberEmail());
    }

}
