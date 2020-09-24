package com.khomchenko.crud.configuration;

import com.khomchenko.crud.dto.ExperienceDto;

public interface MicroserviceRequest {

    Object sendGetRequest(String url, String jwt);

    Object sendPostRequest(String jwt, ExperienceDto entity);
}
