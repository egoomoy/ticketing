package com.example.my.ticket.domain;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.my.ticket.domain.QBookingBlackAndWhiteDto_Combine is a Querydsl Projection type for Combine
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBookingBlackAndWhiteDto_Combine extends ConstructorExpression<BookingBlackAndWhiteDto.Combine> {

    private static final long serialVersionUID = -2099825153L;

    public QBookingBlackAndWhiteDto_Combine(com.querydsl.core.types.Expression<Long> combineNo, com.querydsl.core.types.Expression<BlackAndWhiteCombineType> combineType, com.querydsl.core.types.Expression<Long> combineValue) {
        super(BookingBlackAndWhiteDto.Combine.class, new Class<?>[]{long.class, BlackAndWhiteCombineType.class, long.class}, combineNo, combineType, combineValue);
    }

}

