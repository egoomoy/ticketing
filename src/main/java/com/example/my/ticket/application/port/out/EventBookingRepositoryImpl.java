package com.example.my.ticket.application.port.out;


import com.example.my.ticket.adapter.out.persistence.*;
import com.example.my.ticket.domain.BookingBlackAndWhiteDto;
import com.example.my.ticket.domain.QBookingBlackAndWhiteDto_Combine;
import com.example.my.ticket.domain.QBookingBlackAndWhiteDto_GroupDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class EventBookingRepositoryImpl implements EventBookingRepository {
    private final EventBookingJpaRepository eventBookingJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(EventBooking eventBooking) {
        eventBookingJpaRepository.save(eventBooking);
    }

    @Override
    @Cacheable(value = "blackAndWhiteList", key = "#eventNo")
    public List<BookingBlackAndWhiteDto.GroupDto> getBlackAndWhiteByEventNo(Long eventNo) {
        QBookingBlackAndWhiteGroup qBlackAndWhiteGroup = new QBookingBlackAndWhiteGroup("qBlackAndWhiteGroup");
        QBookingBlackAndWhiteCombiner qBlackAndWhiteCombiner = new QBookingBlackAndWhiteCombiner("qBlackAndWhiteCombiner");

        Map<Long, BookingBlackAndWhiteDto.GroupDto> resultMap = jpaQueryFactory
                .select(qBlackAndWhiteGroup.groupNo,
                        qBlackAndWhiteGroup.blackAndWhiteType,
                        qBlackAndWhiteGroup.combineOperator,
                        qBlackAndWhiteCombiner.combineNo,
                        qBlackAndWhiteCombiner.combineType
                )
                .from(qBlackAndWhiteCombiner)
                .innerJoin(qBlackAndWhiteCombiner.blackAndWhiteGroup, qBlackAndWhiteGroup)
                .where(qBlackAndWhiteGroup.event.eventNo.eq(eventNo))
                .transform(groupBy(qBlackAndWhiteGroup.groupNo).as(new QBookingBlackAndWhiteDto_GroupDto(
                        qBlackAndWhiteGroup.groupNo,
                        qBlackAndWhiteGroup.blackAndWhiteType,
                        qBlackAndWhiteGroup.combineOperator,
                        list(new QBookingBlackAndWhiteDto_Combine(qBlackAndWhiteCombiner.combineNo,
                                qBlackAndWhiteCombiner.combineType,
                                qBlackAndWhiteCombiner.combineValue
                        ))
                )));


        List<BookingBlackAndWhiteDto.GroupDto> collect = resultMap.keySet().stream()
                .map(resultMap::get)
                .collect(toList());

        return collect;
    }

    public boolean existsRegisterUser(Long userNo, Long eventSequenceNo) {
        QEventBooking eventBooking = new QEventBooking("qEventBooking");
        Integer fetchOne = jpaQueryFactory.selectOne().from(eventBooking)
                .where(eventBooking.userNo.eq(userNo).and(eventBooking.eventSequence.eventSequenceNo.eq(eventSequenceNo)))
                .fetchFirst();
        return fetchOne != null;
    }
}
