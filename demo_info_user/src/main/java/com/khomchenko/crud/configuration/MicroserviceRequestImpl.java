package com.khomchenko.crud.configuration;

import com.khomchenko.crud.dto.ExperienceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
    public Object sendGetRequest(String url, String jwt) {
        return webClientBuilder.build()
                .get()
                .uri(url)
                .header(authorizationHeader, jwt)
                .retrieve()
                .bodyToMono(ExperienceDto.class)
                .block();
    }

    @Override
    public Object sendPostRequest(String jwt, ExperienceDto entity) {
        return webClientBuilder.build()
                .post().uri(experienceApiUrl)
                .header(authorizationHeader, jwt)
                .body(Mono.just(entity), ExperienceDto.class)
                .retrieve()
                .bodyToMono(ExperienceDto.class).block();
    }
}
