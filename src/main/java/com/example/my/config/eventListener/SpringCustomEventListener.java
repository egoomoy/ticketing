package com.example.my.config.eventListener;

import com.example.my.config.kafka.AbstractEvent;
import com.example.my.ticket.application.service.PaymentEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringCustomEventListener implements CustomEventListener {
    @Override
    @EventListener
    public void handleEvent(Object event) {
        if (event instanceof PaymentEvent) {
            PaymentEvent paymentEvent = (PaymentEvent) event;
            System.out.println("스프링 이벤트 - payment : " + paymentEvent.getUserNo());
        } else {
            System.out.println("SpringCustomEventListener error");
        }
    }
}