package com.example.my.ticket.domain;

import com.example.my.ticket.application.port.out.BlackAndWhiteCombineOperator;
import com.example.my.ticket.application.port.out.BlackAndWhiteCombineType;
import com.example.my.ticket.application.port.out.BlackAndWhiteType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class BookingBlackAndWhiteDto {

    @Getter
    @NoArgsConstructor
    public static class GroupDto {
        private Long groupNo;
        private BlackAndWhiteType blackAndWhiteType;
        private BlackAndWhiteCombineOperator combineOperator;
        private List<Combine> combines;

        @Builder
        @QueryProjection
        public GroupDto(Long groupNo, BlackAndWhiteType blackAndWhiteType, BlackAndWhiteCombineOperator combineOperator, List<Combine> combines) {
            this.groupNo = groupNo;
            this.blackAndWhiteType = blackAndWhiteType;
            this.combineOperator = combineOperator;
            this.combines = combines;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Combine {
        private Long combineNo;
        private BlackAndWhiteCombineType combineType;
        private Long combineValue;

        @Builder
        @QueryProjection
        public Combine(Long combineNo, BlackAndWhiteCombineType combineType, Long combineValue) {
            this.combineNo = combineNo;
            this.combineType = combineType;
            this.combineValue = combineValue;
        }
    }
}
