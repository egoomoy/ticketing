package com.example.my.ticket.application.service;

import com.example.my.config.kafka.EventPublisher;
import com.example.my.config.redis.RedissonLock;
import com.example.my.ticket.adapter.out.persistence.*;
import com.example.my.ticket.application.port.in.BookingUseCase;
import com.example.my.ticket.domain.BookingMapper;
import com.example.my.ticket.domain.BookingSaveReqDto;
import com.example.my.ticket.domain.UserResDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PostPersist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(readOnly = true)
@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService implements BookingUseCase {

    private final EventSequenceRepository eventSequenceRepository;
    private final UserInfoModuleClient userInfoModuleClient;
    private final BookingMapper bookingMapper;
    private final EventBookingValidator eventBookingValidator;
    private final EventBookingRepository eventBookingRepository;

    private final EventPublisher eventPublisher;


    @RedissonLock(key = "#bookingSaveReqDto.eventSequenceId")
    @Transactional
    public void booking(BookingSaveReqDto bookingSaveReqDto) {
        EventSequence eventSequence = getByEventSequenceId(bookingSaveReqDto.getEventSequenceId());
        eventSequence.decrease();

        UserResDto.UserInfo userResDto = getByUserId(bookingSaveReqDto.getUserId());
        EventBooking entity = bookingMapper.toEntity(eventSequence, userResDto);

        entity.enroll(eventBookingValidator, userResDto);
        eventBookingRepository.save(entity);
    }

    private EventSequence getByEventSequenceId(UUID uuid) {
        return eventSequenceRepository.findByEventSequenceId(uuid).orElseThrow(() -> new EntityNotFoundException("해당 차수가 없습니다."));
    }

    private UserResDto.UserInfo getByUserId(UUID uuid) {
        return userInfoModuleClient.findByUserId(uuid).orElseThrow(() -> new EntityNotFoundException("해당 유저가 없습니다."));
    }
}
