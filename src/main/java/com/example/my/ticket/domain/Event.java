package com.example.my.ticket.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.UUID;

@Configurable
@Entity
@NoArgsConstructor
@Getter
public class Event {

    @PrePersist
    public void prePersist() {
        this.eventId = UUID.randomUUID();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_no")
    private Long eventNo;

    @Column(nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID eventId;

    @Column(nullable = true, length = 200)
    private String eventName;

    @Builder
    public Event(Long eventNo, UUID eventId, String eventName) {
        this.eventNo = eventNo;
        this.eventId = eventId;
        this.eventName = eventName;
    }
}
