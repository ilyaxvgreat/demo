package com.khomchenko.info.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String name;
    private String password;
    private String lastName;
    private Set<Authority> authorities;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
}
