package com.example.my.ticket.application.port.in;

import com.example.my.ticket.domain.EventSequence;

import java.util.UUID;

public interface EventSequenceUseCase {
    EventSequence getByEventSequenceId(UUID uuid);
    EventSequence getByEventSequenceNo(Long seqNo);
}
