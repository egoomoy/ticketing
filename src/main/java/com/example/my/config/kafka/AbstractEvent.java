package com.example.my.config.kafka;

import com.example.my.TicketApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class AbstractEvent {
    private String eventType;
    private String timestamp;

    public AbstractEvent() {
        String topic_env_prefix = TicketApplication.applicationContext.getEnvironment().getProperty("kafka.topic_prefix");
        this.setEventType(topic_env_prefix + "-" + this.getClass().getSimpleName());
        SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
        this.timestamp = defaultSimpleDateFormat.format(new Date());
    }

    public void publish() {
        EventPublisherConfig eventPublisherConfig = TicketApplication.applicationContext.getBean(EventPublisherConfig.class);
        eventPublisherConfig.eventPublisher().publishEvent(this);
    }
}