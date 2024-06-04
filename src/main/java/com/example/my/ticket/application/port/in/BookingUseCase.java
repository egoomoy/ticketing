package com.example.my.ticket.application.port.in;

import com.example.my.ticket.domain.BookingSaveReqDto;

public interface BookingUseCase {
    void booking(BookingSaveReqDto bookingSaveReqDto);
}
