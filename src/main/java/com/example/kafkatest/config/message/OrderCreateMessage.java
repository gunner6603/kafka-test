package com.example.kafkatest.config.message;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
public class OrderCreateMessage {

    private Long orderId;
    private String memberEmail;
}
