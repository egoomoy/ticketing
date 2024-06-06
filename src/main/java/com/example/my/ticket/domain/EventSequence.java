package com.example.my.ticket.domain;


import com.example.my.ticket.domain.Event;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate
public class EventSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_sequence_no")
    private Long eventSequenceNo;

    @Column(nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID eventSequenceId;

    @Column
    private LocalDateTime enrollmentStartDate;

    @Column
    private LocalDateTime enrollmentEndDate;

    @Column
    private LocalDateTime eventSequenceStartDate;

    @Column
    private LocalDateTime eventSequenceEndDate;


    private Long quota;

    @ManyToOne
    @JoinColumn(name = "event_no")
    private Event event;

    public void decrease() {
        validateQuotaCount();
        this.quota -= 1;
    }

    private void validateQuotaCount() {
        if (quota < 1) {
            throw new RuntimeException("수강 신청 정원 초과");
        }
    }

    @Builder
    public EventSequence(Long eventSequenceNo, UUID eventSequenceId, LocalDateTime enrollmentStartDate, LocalDateTime enrollmentEndDate, LocalDateTime eventSequenceStartDate, LocalDateTime eventSequenceEndDate, Event event, Long quota) {
        this.eventSequenceNo = eventSequenceNo;
        this.eventSequenceId = eventSequenceId;
        this.enrollmentStartDate = enrollmentStartDate;
        this.enrollmentEndDate = enrollmentEndDate;
        this.eventSequenceStartDate = eventSequenceStartDate;
        this.eventSequenceEndDate = eventSequenceEndDate;
        this.event = event;
        this.quota = quota;
    }
}
