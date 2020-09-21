package com.khomchenko.auth.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String name;
    private String password;

}
