package com.example.my.ticket.adapter.out.persistence;

import com.example.my.ticket.domain.BlackAndWhiteCombineOperator;
import com.example.my.ticket.domain.BlackAndWhiteType;
import com.example.my.ticket.domain.BookingBlackAndWhiteDto;
import com.example.my.ticket.domain.UserResDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventBookingValidator {
    private final EventSequenceRepository eventSequenceRepository;
    private final EventBookingRepository eventBookingRepository;

    public void validate(EventBooking eventBooking, UserResDto.UserInfo userInfo) {
        validate(eventBooking, userInfo, getEventSequence(eventBooking));
    }

    /**
     * todo: 수강 신청 제한 조건 추가 확인 필요
     *
     * @param eventBooking 수강 신청 정보
     * @param userInfo           유저 정보
     * @param eventSequence     차수 정보
     */
    private void validate(EventBooking eventBooking, UserResDto.UserInfo userInfo, EventSequence eventSequence) {
//        if (eventBookingRepository.existsRegisterUser(userInfo.getUserNo(), eventSequence.getEventSequenceNo())) {
//            // 기수강 신청 에러
//            throw new BusinessCustomException("");
//        }


        LocalDateTime enrollmentStartDate = eventSequence.getEnrollmentStartDate();
        LocalDateTime enrollmentEndDate = eventSequence.getEnrollmentEndDate();
        if (!isWithinEnrollmentPeriod(enrollmentStartDate, enrollmentEndDate)) {
            // 수강신청 신청 기간 에러
            throw new RuntimeException("");
        }

        List<BookingBlackAndWhiteDto.GroupDto> blackAndWhiteList = getBookingBlackAndWhite(eventBooking);
        boolean blackCondition = verifyBlackAndWhiteList(blackAndWhiteList, BlackAndWhiteType.BLACK, userInfo);
        boolean whiteCondition = verifyBlackAndWhiteList(blackAndWhiteList, BlackAndWhiteType.WHITE, userInfo);

        // 블랙 아닐 때 (false) -> 화리에 없으면 !white는 true => true 예외
        // 블랙 아닐 때 (false) -> 화리에 있으면 !white는 false => false
        // 블랙일 때 (true) -> 화리에 없으면 !white는 true => true 예외
        // 블랙일 때 (true) -> 화리에 있으면 !white는 false => true 예외
        if (blackCondition || !whiteCondition) {
            throw new RuntimeException("수강 신청 제약 대상자 입니다.");
        }
    }

    /**
     * @param blackAndWhiteList 접근 제한 목록
     * @param checkType         접근 제한 타입
     * @param userInfo          유저 정보
     * @return 제한 여부
     */
    private boolean verifyBlackAndWhiteList(List<BookingBlackAndWhiteDto.GroupDto> blackAndWhiteList, BlackAndWhiteType checkType, UserResDto.UserInfo userInfo) {
        boolean defaultConditionByType = BlackAndWhiteType.WHITE.equals(checkType);

        boolean verifiedCondition = defaultConditionByType;
        List<Boolean> conditions = new ArrayList<>();

        for (BookingBlackAndWhiteDto.GroupDto groupDto : blackAndWhiteList) {
            if (Objects.equals(groupDto.getBlackAndWhiteType(), checkType) && !groupDto.getCombines().isEmpty()) {
                BlackAndWhiteCombineOperator combineOperator = groupDto.getCombineOperator();
                for (BookingBlackAndWhiteDto.Combine combine : groupDto.getCombines()) {
                    switch (combine.getCombineType()) {
                        case JOB_ROLE -> {
                            boolean jobRoleCondition = Optional.ofNullable(userInfo.getJobRoleNo())
                                    .map(jobRoleNo -> Objects.equals(jobRoleNo, combine.getCombineValue()))
                                    .orElse(false);
                            conditions.add(jobRoleCondition);
                        }
                        case ORG -> {
                            boolean orgCondition = Optional.ofNullable(userInfo.getOrgNo())
                                    .map(orgNo -> Objects.equals(orgNo, combine.getCombineValue()))
                                    .orElse(false);
                            conditions.add(orgCondition);
                        }
                        default -> throw new RuntimeException();
                    }
                }

                if (combineOperator == BlackAndWhiteCombineOperator.OR) {
                    verifiedCondition = conditions.stream().anyMatch(condition -> condition);
                } else if (combineOperator == BlackAndWhiteCombineOperator.AND) {
                    verifiedCondition = conditions.stream().allMatch(condition -> condition);
                }
            }
        }

        return verifiedCondition;
    }

    /**
     * @param eventBooking 수강 신청 정보
     * @return 차수 정보
     */
    private EventSequence getEventSequence(EventBooking eventBooking) {
        return eventSequenceRepository.findById(eventBooking.getEventSequence().getEventSequenceNo()).orElseThrow(() -> new EntityNotFoundException("해당 차수가 없습니다."));
    }

    /**
     * @param eventBooking 수강 신청 정보
     * @return 접근 제한 목록
     */
    private List<BookingBlackAndWhiteDto.GroupDto> getBookingBlackAndWhite(EventBooking eventBooking) {
        EventSequence eventSequence = this.getEventSequence(eventBooking);
        return eventBookingRepository.getBlackAndWhiteByEventNo(eventSequence.getEvent().getEventNo());
    }

    /**
     * @param enrollmentStartDate 수강 신청 시작일
     * @param enrollmentEndDate   수강 신청 종료일
     * @return 등록 가능 여부
     */
    private boolean isWithinEnrollmentPeriod(LocalDateTime enrollmentStartDate, LocalDateTime enrollmentEndDate) {
        LocalDateTime now = LocalDateTime.now();
        return (now.isAfter(enrollmentStartDate) || now.isEqual(enrollmentStartDate)) && (now.isBefore(enrollmentEndDate) || now.isEqual(enrollmentEndDate));
    }
}
