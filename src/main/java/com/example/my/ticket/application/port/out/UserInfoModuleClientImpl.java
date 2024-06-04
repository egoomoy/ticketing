package com.example.my.ticket.application.port.out;

import com.example.my.ticket.adapter.out.persistence.UserInfoModuleClient;
import com.example.my.ticket.domain.UserResDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserInfoModuleClientImpl implements UserInfoModuleClient {

    private final ObjectMapper objectMapper;

    @Override
    public Optional<UserResDto.UserInfo> findByUserId(UUID uuid) {
        return Optional.ofNullable(UserResDto.UserInfo.builder()
                .userId(UUID.fromString("b6ef606f-08f4-48d9-8078-36c0bb605734"))
                .userNo(1L)
                .orgName("HAE")
                .orgNo(1L)
                .jobRoleName("판매직")
                .jobRoleNo(1L)
                .accountId("koo9@naver.com")
                .email("koo9@nvaer.com")
                .build());
    }
}
