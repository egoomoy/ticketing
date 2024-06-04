package com.example.my.ticket.adapter.in.web;

import com.example.my.ticket.application.port.in.BookingUseCase;
import com.example.my.ticket.domain.BookingSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookingController {

    private final BookingUseCase bookingService;

    @PostMapping("/booking")
    public void enroll(@RequestBody BookingSaveReqDto bookingSaveReqDto) {
        bookingService.booking(bookingSaveReqDto);
    }
}
