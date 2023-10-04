package com.example.kafkatest.controller;

import com.example.kafkatest.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public void createOrder(@RequestParam String productName, @RequestParam String memberEmail) {
        orderService.createOrder(productName, memberEmail);
    }
}
