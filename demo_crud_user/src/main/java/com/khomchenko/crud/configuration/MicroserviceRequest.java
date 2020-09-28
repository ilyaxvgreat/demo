package com.khomchenko.crud.configuration;

import com.khomchenko.crud.dto.AuthenticationRequestDto;
import com.khomchenko.crud.dto.AuthenticationsResponseDto;
import com.khomchenko.crud.dto.UserDto;
import com.khomchenko.crud.dto.UserExperienceDto;

import java.util.List;

public interface MicroserviceRequest {

    List<UserExperienceDto> sendGetListRequest(String url, String jwt);

    Object sendGetRequest(String url, String jwt, Class<?> response);

    AuthenticationsResponseDto sendPostRequestForLogin(AuthenticationRequestDto userDto);

    void sendDeleteRequest(String url, String jwt);

    UserDto sendPostRequest(String url, String jwt,UserDto userDto);
}
