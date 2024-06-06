package com.example.my.ticket.application.service;

import com.example.my.ticket.application.port.in.CustomEventListener;
import com.example.my.ticket.domain.PaymentEvent;
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
           // ServletRequestHandledEvent, ApplicationStartedEvent, ApplicationReadyEvent, ConsumerStartingEvent, ConsumerStartedEvent 이런 것도 캐치
        }
    }
}