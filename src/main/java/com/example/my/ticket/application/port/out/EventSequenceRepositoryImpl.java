package com.example.my.ticket.application.port.out;

import com.example.my.ticket.adapter.out.persistence.EventSequence;
import com.example.my.ticket.adapter.out.persistence.EventSequenceJpaRepository;
import com.example.my.ticket.adapter.out.persistence.EventSequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EventSequenceRepositoryImpl implements EventSequenceRepository {
    private final EventSequenceJpaRepository eventSequenceJpaRepository;

    @Override
    public Optional<EventSequence> findByEventSequenceId(UUID uuid) {
        return eventSequenceJpaRepository.findByEventSequenceId(uuid);
    }

    @Override
    public Optional<EventSequence> findById(Long seqNo) {
        return eventSequenceJpaRepository.findById(seqNo);
    }


}
