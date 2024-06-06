package com.example.my.config.kafka;

import com.example.my.TicketApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventPublisher implements EventPublisher {

    @Override
    public void publishEvent(AbstractEvent event) {
        KafkaTemplate kafkaTemplate = TicketApplication.applicationContext.getBean(KafkaTemplate.class);
        String json = this.toJson(event);
        kafkaTemplate.send("custom-events", String.valueOf(event.getKey()), json);
    }

    public String toJson(AbstractEvent event) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON format exception", e);
        }

        return json;
    }
}