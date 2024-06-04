package com.example.my.ticket.domain;

import com.example.my.ticket.adapter.out.persistence.EventBooking;
import com.example.my.ticket.adapter.out.persistence.EventSequence;
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
