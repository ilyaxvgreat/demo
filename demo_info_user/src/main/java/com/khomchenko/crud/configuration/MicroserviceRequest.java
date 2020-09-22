package com.khomchenko.crud.configuration;

public interface MicroserviceRequest {

    Object sendGetRequest(String url, String jwt, Class<?> response);

}
