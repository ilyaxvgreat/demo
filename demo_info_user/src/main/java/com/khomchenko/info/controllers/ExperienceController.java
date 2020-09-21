package com.khomchenko.info.controllers;

import com.khomchenko.info.dto.ExperienceDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/exps")
@AllArgsConstructor
public class ExperienceController {

    private final WebClient.Builder webClientBuilder;

    @GetMapping
    public List<ExperienceDto> findAll(@RequestHeader("Authorization") String jwt) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/exps")
                .header("Authorization", jwt)
                .retrieve()
                .toEntityList(ExperienceDto.class)
                .block()
                .getBody();
    }

}
