package com.example.my.ticket.application.service;

import com.example.my.config.redis.RedissonLock;
import com.example.my.ticket.application.port.in.BookingSaveReqDto;
import com.example.my.ticket.application.port.in.BookingUseCase;
import com.example.my.ticket.application.port.in.EventSequenceUseCase;
import com.example.my.ticket.application.port.out.*;
import com.example.my.ticket.domain.*;
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
public class BookingService implements BookingUseCase {

    private final EventSequenceUseCase eventSequenceUseCase;
    private final UserInfoModuleClient userInfoModuleClient;
    private final BookingMapper bookingMapper;
    private final EventBookingValidator eventBookingValidator;
    private final EventBookingRepository eventBookingRepository;

    @RedissonLock(key = "#bookingSaveReqDto.eventSequenceId")
    @Transactional
    public void booking(BookingSaveReqDto bookingSaveReqDto) {
        EventSequence eventSequence = eventSequenceUseCase.getByEventSequenceId(bookingSaveReqDto.getEventSequenceId());
        eventSequence.decrease();

        UserResDto.UserInfo userResDto = getByUserId(bookingSaveReqDto.getUserId());
        EventBooking entity = bookingMapper.toEntity(eventSequence, userResDto);

        entity.enroll(eventBookingValidator, userResDto.getUserId());
        eventBookingRepository.save(entity);

        PaymentEvent event = PaymentEvent.builder().userNo(entity.getUserNo()).key(entity.getEventSequence().getEventSequenceNo()).build();
        event.publish();
    }

    private UserResDto.UserInfo getByUserId(UUID uuid) {
        return userInfoModuleClient.findByUserId(uuid).orElseThrow(() -> new EntityNotFoundException("해당 유저가 없습니다."));
    }
}
