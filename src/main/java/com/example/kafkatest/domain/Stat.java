package com.example.kafkatest.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stats")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Stat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String productName;

    private Long count;

    public void increaseCount() {
        count += 1;
    }
}

