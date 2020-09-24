package com.khomchenko.crud.configuration;

import com.khomchenko.crud.dto.AuthenticationsResponseDto;
import com.khomchenko.crud.dto.UserDto;

public interface MicroserviceRequest {

    Object sendGetRequest(String url, String jwt, Class<?> response);

    AuthenticationsResponseDto sendPostRequestForLogin(UserDto userDto);

    void sendDeleteRequest(String url, String jwt);

    UserDto sendPostRequest(String url, String jwt,UserDto userDto);
}
