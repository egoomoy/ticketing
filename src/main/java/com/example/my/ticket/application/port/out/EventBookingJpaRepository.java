package com.example.my.ticket.application.port.out;

import com.example.my.ticket.domain.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventBookingJpaRepository extends JpaRepository<EventBooking, Long> {
}
