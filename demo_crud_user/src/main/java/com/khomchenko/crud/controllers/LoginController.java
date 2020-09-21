package com.khomchenko.crud.controllers;

import com.khomchenko.crud.dto.AuthenticationsResponseDto;
import com.khomchenko.crud.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class LoginController {

    @Value("${login.service.url}")
    private String loginUrlApi;

    private final WebClient.Builder webClientBuilder;

    public LoginController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostMapping(path = "/login")
    public Object login(@RequestBody UserDto userDto) {
        try {
            Mono<AuthenticationsResponseDto> authenticationsResponseDtoMono = webClientBuilder.build()
                    .post()
                    .uri(loginUrlApi)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(userDto))
                    .retrieve()
                    .bodyToMono(AuthenticationsResponseDto.class);
            return authenticationsResponseDtoMono.block();
        } catch (WebClientResponseException e) {
            return new ResponseEntity<>("invalid name/password", HttpStatus.FORBIDDEN);
        }
    }
}
