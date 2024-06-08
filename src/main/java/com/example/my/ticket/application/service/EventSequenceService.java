package com.example.my.ticket.application.service;

import com.example.my.ticket.application.port.in.EventSequenceUseCase;
import com.example.my.ticket.application.port.out.EventSequenceRepository;
import com.example.my.ticket.domain.EventSequence;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(readOnly = true)
@Slf4j
@Service
@RequiredArgsConstructor
public class EventSequenceService implements EventSequenceUseCase {
    private final EventSequenceRepository eventSequenceRepository;

    @Override
    public EventSequence getByEventSequenceId(UUID uuid) {
        return eventSequenceRepository.findByEventSequenceId(uuid).orElseThrow(() -> new EntityNotFoundException("해당 차수가 없습니다."));
    }

    @Override
    @Cacheable(value = "eventSequenceByNo", key = "#seqNo")
    public EventSequence getByEventSequenceNo(Long seqNo) {
        return eventSequenceRepository.findById(seqNo).orElseThrow(() -> new EntityNotFoundException("해당 차수가 없습니다."));
    }
}
