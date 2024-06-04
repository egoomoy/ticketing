package com.example.my.ticket.domain;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.my.ticket.domain.QBookingBlackAndWhiteDto_GroupDto is a Querydsl Projection type for GroupDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBookingBlackAndWhiteDto_GroupDto extends ConstructorExpression<BookingBlackAndWhiteDto.GroupDto> {

    private static final long serialVersionUID = 448721440L;

    public QBookingBlackAndWhiteDto_GroupDto(com.querydsl.core.types.Expression<Long> groupNo, com.querydsl.core.types.Expression<BlackAndWhiteType> blackAndWhiteType, com.querydsl.core.types.Expression<BlackAndWhiteCombineOperator> combineOperator, com.querydsl.core.types.Expression<? extends java.util.List<BookingBlackAndWhiteDto.Combine>> combines) {
        super(BookingBlackAndWhiteDto.GroupDto.class, new Class<?>[]{long.class, BlackAndWhiteType.class, BlackAndWhiteCombineOperator.class, java.util.List.class}, groupNo, blackAndWhiteType, combineOperator, combines);
    }

}

