package com.example.my.ticket.application.port.out;


import com.example.my.ticket.domain.BookingBlackAndWhiteDto;
import com.example.my.ticket.domain.EventBooking;

import java.util.List;

public interface EventBookingRepository {
    void save(EventBooking eventBooking);

    List<BookingBlackAndWhiteDto.GroupDto> getBlackAndWhiteByEventNo(Long eventNo);

    boolean existsRegisterUser(Long userNo, Long eventSequenceNo);
}
