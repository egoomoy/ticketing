package com.example.my.ticket.domain;

import com.example.my.config.EnumInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlackAndWhiteType implements EnumInterface {
    BLACK("BLACK"),
    WHITE("WHITE");

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
