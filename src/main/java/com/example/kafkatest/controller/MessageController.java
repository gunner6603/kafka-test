package com.example.kafkatest.controller;

import com.example.kafkatest.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public void send(@RequestParam("message") String message) {
        messageService.sendTestMessage(message);
    }

}
