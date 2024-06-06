package com.example.my.ticket.application.service;

import com.example.my.config.kafka.AbstractEvent;
import com.example.my.config.kafka.EventPublisher;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

@Getter
@NoArgsConstructor
public class PaymentEvent extends AbstractEvent {
    private Long userNo;

    @Builder
    public PaymentEvent(Long userNo) {
        this.userNo = userNo;
    }
}