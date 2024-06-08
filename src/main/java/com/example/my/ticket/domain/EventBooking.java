package com.example.my.ticket.domain;


import com.example.my.ticket.application.port.out.EventBookingValidator;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class EventBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_registration_no")
    private Long eventBookingNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventSequence_no")
    private EventSequence eventSequence;

    @Column
    private Long userNo;

    @Column
    private LocalDateTime eventSequenceStartDate;

    @Column
    private LocalDateTime eventSequenceEndDate;


    public void enroll(EventBookingValidator eventBookingValidator, UUID userId) {
        eventBookingValidator.validate(this, userId);
    }

    @Builder
    public EventBooking(Long eventBookingNo, Long eventSequenceNo, Long userNo, LocalDateTime eventSequenceStartDate, LocalDateTime eventSequenceEndDate) {
        this.eventBookingNo = eventBookingNo;
        this.userNo = userNo;
        this.eventSequenceStartDate = eventSequenceStartDate;
        this.eventSequenceEndDate = eventSequenceEndDate;
        if (eventSequenceNo != null) {
            this.eventSequence = EventSequence.builder().eventSequenceNo(eventSequenceNo).build();
        }
    }
}
