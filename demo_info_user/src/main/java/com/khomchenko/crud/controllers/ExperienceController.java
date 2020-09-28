package com.khomchenko.crud.controllers;

import com.khomchenko.crud.configuration.Headers;
import com.khomchenko.crud.configuration.MicroserviceRequest;
import com.khomchenko.crud.dto.ExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experiences")
public class ExperienceController {

    @Value("${experience.api.url}")
    private transient String apiUrl;

    private static final String HEADER = "Authorization";

    private final transient MicroserviceRequest microserviceRequest;

    public ExperienceController(MicroserviceRequest microserviceRequest) {
        this.microserviceRequest = microserviceRequest;
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable("id") Long id, @RequestHeader(HEADER) String jwt) {
        return microserviceRequest
                .sendGetRequest(apiUrl + id, jwt);
    }

    @GetMapping("/users/{userId}")
    public List<ExperienceDto> getByUserId(@PathVariable("userId") Long id, @RequestHeader("Authorization") String jwt) {
        return microserviceRequest
                .sendGetListRequest(apiUrl + "users/" + id, jwt);
    }

    @DeleteMapping("/{experienceId}")
    public void deleteById(@PathVariable Long experienceId, @RequestHeader(HEADER) String jwt) {
        microserviceRequest.sendDeleteRequest(experienceId, jwt);
    }


    @PostMapping
    public Object save(@RequestBody ExperienceDto experienceDto, @RequestHeader(HEADER) String jwt) {
        return microserviceRequest.sendPostRequest(jwt, experienceDto);
    }

}
