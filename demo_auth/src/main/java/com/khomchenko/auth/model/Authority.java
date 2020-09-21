package com.khomchenko.auth.model;

import lombok.Getter;

@Getter
public enum Authority {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_DEFAULT("ROLE_DEFAULT");

    private final String name;

    Authority(String name) {
        this.name = name;
    }
}
