package com.example.my.config.kafka;

public interface EventPublisher {
    void publishEvent(AbstractEvent event);
}