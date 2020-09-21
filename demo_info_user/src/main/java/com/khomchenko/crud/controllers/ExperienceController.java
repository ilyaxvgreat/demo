package com.khomchenko.crud.controllers;

import com.khomchenko.crud.dto.ExperienceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/exps")
public class ExperienceController {

    @Value("${jwt.header}")
    private String authorizationHeader;

    @Value("${experience.api.url}")
    private String apiUrl;

    private final WebClient.Builder webClientBuilder;

    public ExperienceController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping
    public ResponseEntity<List<ExperienceDto>> findAll(@RequestHeader("Authorization") String jwt) {
        return webClientBuilder.build()
                .get()
                .uri(apiUrl)
                .header(authorizationHeader, jwt)
                .retrieve()
                .toEntityList(ExperienceDto.class)
                .block();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDto> getById(@PathVariable Long id, @RequestHeader("Authorization") String jwt) {
        return webClientBuilder.build()
                .get()
                .uri(apiUrl + "/" + id)
                .header(authorizationHeader, jwt)
                .retrieve()
                .toEntity(ExperienceDto.class)
                .block();
    }

}
