package com.example.biter.Domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
