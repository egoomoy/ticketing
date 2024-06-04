package com.example.my.ticket.adapter.out.persistence;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookingBlackAndWhiteGroup is a Querydsl query type for BookingBlackAndWhiteGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookingBlackAndWhiteGroup extends EntityPathBase<BookingBlackAndWhiteGroup> {

    private static final long serialVersionUID = 99489160L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookingBlackAndWhiteGroup bookingBlackAndWhiteGroup = new QBookingBlackAndWhiteGroup("bookingBlackAndWhiteGroup");

    public final EnumPath<com.example.my.ticket.domain.BlackAndWhiteType> blackAndWhiteType = createEnum("blackAndWhiteType", com.example.my.ticket.domain.BlackAndWhiteType.class);

    public final EnumPath<com.example.my.ticket.domain.BlackAndWhiteCombineOperator> combineOperator = createEnum("combineOperator", com.example.my.ticket.domain.BlackAndWhiteCombineOperator.class);

    public final QEvent event;

    public final NumberPath<Long> groupNo = createNumber("groupNo", Long.class);

    public QBookingBlackAndWhiteGroup(String variable) {
        this(BookingBlackAndWhiteGroup.class, forVariable(variable), INITS);
    }

    public QBookingBlackAndWhiteGroup(Path<? extends BookingBlackAndWhiteGroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookingBlackAndWhiteGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookingBlackAndWhiteGroup(PathMetadata metadata, PathInits inits) {
        this(BookingBlackAndWhiteGroup.class, metadata, inits);
    }

    public QBookingBlackAndWhiteGroup(Class<? extends BookingBlackAndWhiteGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new QEvent(forProperty("event")) : null;
    }

}

