package com.khomchenko.crud.controllers;

import com.khomchenko.crud.configuration.MicroserviceRequest;
import com.khomchenko.crud.dto.ExperienceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experiences")
public class ExperienceController {

    @Value("${experience.api.url}")
    private String apiUrl;

    private final MicroserviceRequest microserviceRequest;

    public ExperienceController(MicroserviceRequest microserviceRequest) {
        this.microserviceRequest = microserviceRequest;
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable("id") Long id, @RequestHeader("Authorization") String jwt) {
        return microserviceRequest
                .sendGetRequest(apiUrl + id, jwt);
    }

    @PostMapping
    public Object save(@RequestBody ExperienceDto experienceDto, @RequestHeader("Authorization") String jwt) {
        return microserviceRequest.sendPostRequest(jwt, experienceDto);
    }

}
