package com.khomchenko.crud.configuration;

import com.khomchenko.crud.dto.ExperienceDto;

import java.util.List;

public interface MicroserviceRequest {

    Object sendGetRequest(String url, String jwt);

    void sendDeleteRequest(Long userId,String jwt);

    Object sendPostRequest(String jwt, ExperienceDto entity);

    List<ExperienceDto> sendGetListRequest(String url, String jwt);
}
