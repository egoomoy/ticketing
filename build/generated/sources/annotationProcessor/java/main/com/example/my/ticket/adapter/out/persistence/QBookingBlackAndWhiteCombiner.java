package com.example.my.ticket.adapter.out.persistence;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookingBlackAndWhiteCombiner is a Querydsl query type for BookingBlackAndWhiteCombiner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookingBlackAndWhiteCombiner extends EntityPathBase<BookingBlackAndWhiteCombiner> {

    private static final long serialVersionUID = -764591254L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookingBlackAndWhiteCombiner bookingBlackAndWhiteCombiner = new QBookingBlackAndWhiteCombiner("bookingBlackAndWhiteCombiner");

    public final QBookingBlackAndWhiteGroup blackAndWhiteGroup;

    public final NumberPath<Long> combineNo = createNumber("combineNo", Long.class);

    public final EnumPath<com.example.my.ticket.domain.BlackAndWhiteCombineType> combineType = createEnum("combineType", com.example.my.ticket.domain.BlackAndWhiteCombineType.class);

    public final NumberPath<Long> combineValue = createNumber("combineValue", Long.class);

    public QBookingBlackAndWhiteCombiner(String variable) {
        this(BookingBlackAndWhiteCombiner.class, forVariable(variable), INITS);
    }

    public QBookingBlackAndWhiteCombiner(Path<? extends BookingBlackAndWhiteCombiner> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookingBlackAndWhiteCombiner(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookingBlackAndWhiteCombiner(PathMetadata metadata, PathInits inits) {
        this(BookingBlackAndWhiteCombiner.class, metadata, inits);
    }

    public QBookingBlackAndWhiteCombiner(Class<? extends BookingBlackAndWhiteCombiner> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blackAndWhiteGroup = inits.isInitialized("blackAndWhiteGroup") ? new QBookingBlackAndWhiteGroup(forProperty("blackAndWhiteGroup"), inits.get("blackAndWhiteGroup")) : null;
    }

}

