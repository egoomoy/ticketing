package com.example.my.config.eventListener;

import com.example.my.config.kafka.AbstractEvent;

public interface CustomEventListener {
    void handleEvent(Object event);
}
