package com.khomchenko.crud.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MicroserviceRequestImpl implements MicroserviceRequest {

    @Value("${jwt.header}")
    private String authorizationHeader;

    @Value("${experience.api.url}")
    private String experienceApiUrl;

    private final WebClient.Builder webClientBuilder;

    public MicroserviceRequestImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Object sendGetRequest(String url , String jwt, Class<?> response) {
        return webClientBuilder.build()
                .get()
                .uri(experienceApiUrl)
                .header(authorizationHeader, jwt)
                .retrieve()
                .bodyToMono(response)
                .block();
    }
}
