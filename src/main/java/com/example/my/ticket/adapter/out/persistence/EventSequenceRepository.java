package com.example.my.ticket.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;

public interface EventSequenceRepository {
    Optional<EventSequence> findByEventSequenceId(UUID uuid);

    Optional<EventSequence> findById(Long seqNo);
}
