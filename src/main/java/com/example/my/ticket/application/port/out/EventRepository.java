package com.example.my.ticket.application.port.out;

import com.example.my.ticket.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByEventId(UUID eventId);

    Event save(Event event);
}
