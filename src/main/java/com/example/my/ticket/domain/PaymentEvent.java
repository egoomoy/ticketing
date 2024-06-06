package com.example.my.ticket.domain;

import com.example.my.config.kafka.AbstractEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentEvent extends AbstractEvent {
    private Long userNo;

    @Builder
    public PaymentEvent(Long userNo, Long key) {
        super.setKey(key);
        this.userNo = userNo;
    }
}