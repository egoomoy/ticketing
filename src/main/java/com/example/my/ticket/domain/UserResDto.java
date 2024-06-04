package com.example.my.ticket.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

public class UserResDto {
    @Getter
    @NoArgsConstructor
    public static class UserInfo {
        private UUID userId;
        private Long userNo;
        private String accountId;
        private String userName;
        private String email;
        private List<String> roles;
        private Long orgNo;
        private String orgName;
        private Long jobRoleNo;
        private String jobRoleName;

        @Builder
        public UserInfo(UUID userId, Long userNo, String accountId, String userName, String email,
                        List<String> roles, Long orgNo, String orgName,
                        Long jobRoleNo, String jobRoleName) {
            this.userId = userId;
            this.userNo = userNo;
            this.accountId = accountId;
            this.userName = userName;
            this.email = email;
            this.roles = roles;
            this.orgNo = orgNo;
            this.orgName = orgName;
            this.jobRoleNo = jobRoleNo;
            this.jobRoleName = jobRoleName;
        }
    }
}
