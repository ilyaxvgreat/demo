package com.khomchenko.crud.dto;

import lombok.Data;

@Data
public class AuthenticationsResponseDto {
    private String username;
    private String token;
}
