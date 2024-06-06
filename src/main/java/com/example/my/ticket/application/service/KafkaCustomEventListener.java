package com.example.my.ticket.application.service;

import com.example.my.ticket.application.port.in.CustomEventListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaCustomEventListener implements CustomEventListener {

    @Override
    @KafkaListener(topics = "custom-events", containerFactory = "KafkaListenerContainerFactory")
    public void handleEvent(Object event) {
        ConsumerRecord<String, String> record = (ConsumerRecord<String, String>) event;
        System.out.println("여기서 하위 로직을 수행한다?");
    }
}