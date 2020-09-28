package com.khomchenko.crud.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String lastName;
    private Set<Authority> authorities;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private List<UserExperienceDto> experience;
}
