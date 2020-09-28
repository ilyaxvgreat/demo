package com.khomchenko.crud.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = 42L;

    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
