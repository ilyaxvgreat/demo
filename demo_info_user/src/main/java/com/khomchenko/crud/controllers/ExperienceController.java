package com.khomchenko.crud.controllers;

import com.khomchenko.crud.configuration.MicroserviceRequest;
import com.khomchenko.crud.dto.ExperienceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exps")
public class ExperienceController {

    @Value("${experience.api.url}")
    private String apiUrl;

    private final MicroserviceRequest microserviceRequest;

    public ExperienceController(MicroserviceRequest microserviceRequest) {
        this.microserviceRequest = microserviceRequest;
    }

    @GetMapping("/{id}")
    public ExperienceDto getById(@PathVariable Long id, @RequestHeader("Authorization") String jwt) {
        return (ExperienceDto) microserviceRequest
                .sendGetRequest(apiUrl + id, jwt, ExperienceDto.class);
    }

}
