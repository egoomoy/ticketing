package com.example.my.ticket.adapter.out.persistence;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventBooking is a Querydsl query type for EventBooking
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventBooking extends EntityPathBase<EventBooking> {

    private static final long serialVersionUID = 598696126L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventBooking eventBooking = new QEventBooking("eventBooking");

    public final NumberPath<Long> eventBookingNo = createNumber("eventBookingNo", Long.class);

    public final QEventSequence eventSequence;

    public final DateTimePath<java.time.LocalDateTime> eventSequenceEndDate = createDateTime("eventSequenceEndDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> eventSequenceStartDate = createDateTime("eventSequenceStartDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public QEventBooking(String variable) {
        this(EventBooking.class, forVariable(variable), INITS);
    }

    public QEventBooking(Path<? extends EventBooking> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventBooking(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventBooking(PathMetadata metadata, PathInits inits) {
        this(EventBooking.class, metadata, inits);
    }

    public QEventBooking(Class<? extends EventBooking> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eventSequence = inits.isInitialized("eventSequence") ? new QEventSequence(forProperty("eventSequence"), inits.get("eventSequence")) : null;
    }

}

