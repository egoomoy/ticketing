package com.example.my.ticket.adapter.out.persistence;


import com.example.my.ticket.domain.UserResDto;

import java.util.Optional;
import java.util.UUID;

public interface UserInfoModuleClient {
    Optional<UserResDto.UserInfo> findByUserId(UUID uuid);
}
