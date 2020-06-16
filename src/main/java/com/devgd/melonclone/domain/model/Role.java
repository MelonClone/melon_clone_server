package com.devgd.melonclone.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    SUPER_ADMIN("ROLE_SUPER_ADMIN"),
    ADMIN("ROLE_ADMIN"),
    ARTIST("ROLE_ARTIST"),
    MEMBER("ROLE_MEMBER");

    private String value;
}