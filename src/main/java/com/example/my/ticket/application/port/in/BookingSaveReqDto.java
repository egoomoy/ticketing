package com.example.my.ticket.application.port.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class BookingSaveReqDto {
    private UUID eventSequenceId;
    private UUID userId;

    @Builder
    public BookingSaveReqDto(UUID eventSequenceId, UUID userId) {
        this.eventSequenceId = eventSequenceId;
        this.userId = userId;
    }
}
