package com.example.my.ticket.application.port.out;


import java.util.Optional;
import java.util.UUID;

public interface UserInfoModuleClient {
    Optional<UserResDto.UserInfo> findByUserId(UUID uuid);
}
