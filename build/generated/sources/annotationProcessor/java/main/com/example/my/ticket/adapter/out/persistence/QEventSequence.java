package com.example.my.ticket.adapter.out.persistence;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventSequence is a Querydsl query type for EventSequence
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventSequence extends EntityPathBase<EventSequence> {

    private static final long serialVersionUID = 723987452L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventSequence eventSequence = new QEventSequence("eventSequence");

    public final DateTimePath<java.time.LocalDateTime> enrollmentEndDate = createDateTime("enrollmentEndDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> enrollmentStartDate = createDateTime("enrollmentStartDate", java.time.LocalDateTime.class);

    public final QEvent event;

    public final DateTimePath<java.time.LocalDateTime> eventSequenceEndDate = createDateTime("eventSequenceEndDate", java.time.LocalDateTime.class);

    public final ComparablePath<java.util.UUID> eventSequenceId = createComparable("eventSequenceId", java.util.UUID.class);

    public final NumberPath<Long> eventSequenceNo = createNumber("eventSequenceNo", Long.class);

    public final DateTimePath<java.time.LocalDateTime> eventSequenceStartDate = createDateTime("eventSequenceStartDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> quota = createNumber("quota", Long.class);

    public QEventSequence(String variable) {
        this(EventSequence.class, forVariable(variable), INITS);
    }

    public QEventSequence(Path<? extends EventSequence> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventSequence(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventSequence(PathMetadata metadata, PathInits inits) {
        this(EventSequence.class, metadata, inits);
    }

    public QEventSequence(Class<? extends EventSequence> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new QEvent(forProperty("event")) : null;
    }

}

