package com.example.my.config.kafka;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EventPublisherConfig {

//    @Autowired
//    private Environment env;

//    private final SpringEventPublisher springEventPublisher;

    private final KafkaEventPublisher kafkaEventPublisher;

    @Bean
    public EventPublisher eventPublisher() {
        return kafkaEventPublisher;
//
//        String publisherType = env.getProperty("event.publisher.type", "spring");
//        if ("kafka".equalsIgnoreCase(publisherType)) {
//            return kafkaEventPublisher;
//        } else {
//            return springEventPublisher;
//        }
    }
}