package com.example.my.config.eventListener;

import com.example.my.config.kafka.AbstractEvent;
import com.example.my.ticket.application.service.PaymentEvent;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaCustomEventListener implements CustomEventListener {

    @Override
    @KafkaListener(topics = "custom-events", groupId = "event-listeners", containerFactory = "KafkaListenerContainerFactory")
    public void handleEvent(Object event) {
        ConsumerRecord<String, String> record = (ConsumerRecord<String, String>) event;
        System.out.println("test");
    }
}