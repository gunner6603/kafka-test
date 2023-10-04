package com.example.kafkatest.controller;

import com.example.kafkatest.infra.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/send")
    public void send(@RequestParam("msg") String message) {
        kafkaProducer.sendMessage(message);
    }

}
