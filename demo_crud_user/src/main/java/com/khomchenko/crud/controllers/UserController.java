package com.khomchenko.crud.controllers;

import com.khomchenko.crud.configuration.MicroserviceRequest;
import com.khomchenko.crud.dto.UserDto;
import com.khomchenko.crud.dto.UserExperienceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Value("${users.api.url}")
    private String userApiUrl;

    @Value("${users.info.api.url}")
    private String usersInfoApiUrl;


    private final WebClient.Builder webClientBuilder;

    private final MicroserviceRequest microserviceRequest;

    public UserController(WebClient.Builder webClientBuilder, MicroserviceRequest microserviceRequest) {
        this.webClientBuilder = webClientBuilder;
        this.microserviceRequest = microserviceRequest;
    }


    @GetMapping("/{userId}/experiences")
    public UserDto getUserByIdWithExperience(@PathVariable Long userId, @RequestHeader(value = "Authorization") String jwt) {
        UserExperienceDto experienceDto = (UserExperienceDto) microserviceRequest
                .sendGetRequest(usersInfoApiUrl + userId, jwt, UserExperienceDto.class);
        UserDto userDto = (UserDto) microserviceRequest.sendGetRequest(userApiUrl + userId, jwt, UserDto.class);
        userDto.setExperience(experienceDto);
        return userDto;
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId, @RequestHeader(value = "Authorization") String jwt) {
        return (UserDto) microserviceRequest.sendGetRequest(userApiUrl + userId, jwt, UserDto.class);

    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId, @RequestHeader(value = "Authorization") String jwt) {
        microserviceRequest.sendDeleteRequest(userApiUrl + userId, jwt);
    }

}
