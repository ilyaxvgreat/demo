package com.khomchenko.info.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/logout")
@AllArgsConstructor
public class LogOutController {

    private final WebClient.Builder webClientBuilder;

    @PostMapping
    public void logOut(@RequestHeader("Authorization") String jwt){
        webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/auth/logout")
                .header("Authorization",jwt)
                .exchange()
                .block();
    }
}
