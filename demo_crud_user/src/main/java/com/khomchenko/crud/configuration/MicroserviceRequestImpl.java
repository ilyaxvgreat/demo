package com.khomchenko.crud.configuration;

import com.khomchenko.crud.dto.AuthenticationsResponseDto;
import com.khomchenko.crud.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MicroserviceRequestImpl implements MicroserviceRequest {

    @Value("${jwt.header}")
    private String authorizationHeader;

    @Value("${login.service.url}")
    private String loginUrlApi;

    private final WebClient.Builder webClientBuilder;

    public MicroserviceRequestImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Object sendGetRequest(String url, String jwt, Class<?> response) {
        return webClientBuilder.build()
                .get()
                .uri(url)
                .header(authorizationHeader, jwt)
                .retrieve()
                .bodyToMono(response)
                .block();
    }

    @Override
    public AuthenticationsResponseDto sendPostRequestForLogin(UserDto userDto){
        return webClientBuilder.build()
                .post()
                .uri(loginUrlApi)
                .body(BodyInserters.fromValue(userDto))
                .retrieve()
                .bodyToMono(AuthenticationsResponseDto.class)
                .block();
    }

    @Override
    public void sendDeleteRequest(String url, String jwt){
        webClientBuilder.build()
                .delete()
                .uri(url)
                .header(authorizationHeader, jwt)
                .retrieve();
    }
}
