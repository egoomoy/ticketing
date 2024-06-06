package com.example.my.ticket.application.service;

import com.example.my.ticket.application.port.out.UserResDto;
import com.example.my.ticket.domain.EventBooking;
import com.example.my.ticket.domain.EventSequence;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public EventBooking toEntity(EventSequence eventSequence, UserResDto.UserInfo userInfo) {
        return EventBooking.builder()
                .eventSequenceNo(eventSequence.getEventSequenceNo())
                .userNo(userInfo.getUserNo())
                .eventSequenceStartDate(eventSequence.getEventSequenceStartDate())
                .eventSequenceEndDate(eventSequence.getEventSequenceEndDate())
                .build();
    }
}
