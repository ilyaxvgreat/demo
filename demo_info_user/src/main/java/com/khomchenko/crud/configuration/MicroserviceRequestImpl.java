package com.khomchenko.crud.configuration;

import com.khomchenko.crud.dto.ExperienceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class MicroserviceRequestImpl implements MicroserviceRequest {

    @Value("${jwt.header}")
    private transient String authorizationHeader;

    @Value("${experience.api.url}")
    private transient String experienceApiUrl;

    private final transient WebClient.Builder webClientBuilder;

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
    public List<ExperienceDto> sendGetListRequest(String url, String jwt) {
        return webClientBuilder.build()
                .get()
                .uri(url)
                .header(authorizationHeader, jwt)
                .retrieve()
                .toEntityList(ExperienceDto.class).block().getBody();
    }

    @Override
    public Object sendPostRequest(String jwt, ExperienceDto entity) {
        return webClientBuilder.build()
                .post()
                .uri(experienceApiUrl)
                .header(authorizationHeader, jwt)
                .body(BodyInserters.fromValue(entity))
                .retrieve()
                .bodyToMono(ExperienceDto.class)
                .block();
    }

    @Override
    public void sendDeleteRequest(Long experienceId, String jwt) {
        webClientBuilder.build()
                .delete()
                .uri(experienceApiUrl + experienceId)
                .header(authorizationHeader, jwt)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
