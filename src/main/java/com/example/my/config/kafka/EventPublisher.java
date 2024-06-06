package com.example.my.config.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface EventPublisher {
    void publishEvent(AbstractEvent event);
}