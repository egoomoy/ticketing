package com.example.my.ticket.application.port.out;

import com.example.my.config.EnumInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlackAndWhiteCombineType implements EnumInterface {
    JOB_ROLE("JOB_ROLE"), // 직무
    JOB_LEVEL("JOB_LEVEL"), // 직위 == 직급
    JOB_TITLE("JOB_TITLE"), // 직책 == 보직
    JOB_GROUP("JOB_GROUP"), // 직군
    ORG("ORG");

    private final String value;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
