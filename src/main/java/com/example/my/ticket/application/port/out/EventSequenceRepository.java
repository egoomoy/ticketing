package com.example.my.ticket.application.port.out;

import com.example.my.ticket.domain.EventSequence;

import java.util.Optional;
import java.util.UUID;

public interface EventSequenceRepository {
    Optional<EventSequence> findByEventSequenceId(UUID uuid);

    Optional<EventSequence> findById(Long seqNo);
}
