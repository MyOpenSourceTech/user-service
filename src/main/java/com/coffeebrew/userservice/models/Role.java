package com.coffeebrew.userservice.models;

public enum Role {
    ROLE_ADMIN, ROLE_CLIENT;

    public String getAuthority() {
        return name();
    }
}