package com.example.my.ticket.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventSequenceJpaRepository extends JpaRepository<EventSequence, Long> {
    Optional<EventSequence> findByEventSequenceId(UUID uuid);
}
