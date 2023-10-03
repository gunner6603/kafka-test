package com.example.kafkatest.repository;

import com.example.kafkatest.domain.Stat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface StatRepository extends JpaRepository<Stat, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Stat> findByProductName(String productName);
}
