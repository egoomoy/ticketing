package com.example.my.ticket.adapter.out.persistence;


import com.example.my.ticket.domain.BookingBlackAndWhiteDto;

import java.util.List;

public interface EventBookingRepository {
    void save(EventBooking eventBooking);

    List<BookingBlackAndWhiteDto.GroupDto> getBlackAndWhiteByEventNo(Long eventNo);

    boolean existsRegisterUser(Long userNo, Long eventSequenceNo);
}
