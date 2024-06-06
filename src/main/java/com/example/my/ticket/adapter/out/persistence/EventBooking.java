package com.example.my.ticket.adapter.out.persistence;


import com.example.my.ticket.application.service.PaymentEvent;
import com.example.my.ticket.domain.UserResDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class EventBooking {

    @PostPersist
    public void onPostPersist() {
        PaymentEvent event = PaymentEvent.builder().userNo(this.userNo).build();
        event.publish();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_registration_no")
    private Long eventBookingNo;

    @ManyToOne
    @JoinColumn(name = "eventSequence_no")
    private EventSequence eventSequence;

    @Column
    private Long userNo;

    @Column
    private LocalDateTime eventSequenceStartDate;

    @Column
    private LocalDateTime eventSequenceEndDate;


    public void enroll(EventBookingValidator eventBookingValidator, UserResDto.UserInfo userInfo) {
        eventBookingValidator.validate(this, userInfo);
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
