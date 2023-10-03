package com.example.kafkatest.service;

import com.example.kafkatest.domain.Stat;
import com.example.kafkatest.repository.StatRepository;
import com.example.kafkatest.service.event.OrderCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StatService {

    private final StatRepository statRepository;

    @TransactionalEventListener
    @Async
    public void updateStat(OrderCreateEvent orderCreateEvent) {
        log.info("update stat for productName={}", orderCreateEvent.getProductName());
        final String productName = orderCreateEvent.getProductName();
        final Stat stat = statRepository.findByProductName(productName)
                .orElseGet(() -> createStat(productName));
        stat.increaseCount();
    }

    private Stat createStat(final String productName) {
        return statRepository.save(Stat.builder()
                                           .productName(productName)
                                           .count(0L)
                                           .build());
    }
}
