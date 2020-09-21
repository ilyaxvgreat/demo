package com.khomchenko.info.controllers;

import com.khomchenko.info.dto.AuthenticationsResponseDto;
import com.khomchenko.info.dto.UserDto;
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
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class LoginController {

    @Value("${login.service.url}")
    private String loginUrlApi;

    private final WebClient.Builder webClientBuilder;

    public LoginController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostMapping
    public Object login(@RequestBody UserDto userDto) {
        log.info("User making login: " + userDto.getName());
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
