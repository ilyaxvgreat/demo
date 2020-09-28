package com.khomchenko.crud.controllers;

import com.khomchenko.crud.configuration.MicroserviceRequest;
import com.khomchenko.crud.dto.UserDto;
import com.khomchenko.crud.dto.UserExperienceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Value("${users.api.url}")
    private transient String userApiUrl;

    @Value("${users.info.api.url}")
    private transient String usersInfoApiUrl;


    private transient final MicroserviceRequest microserviceRequest;

    public UserController(MicroserviceRequest microserviceRequest) {
        this.microserviceRequest = microserviceRequest;
    }


    @GetMapping("/{userId}")
    public UserDto getUserByIdWithExperience(@PathVariable Long userId, @RequestHeader(value = "Authorization") String jwt) {
        List<UserExperienceDto> userExperienceDtos = microserviceRequest
                .sendGetListRequest(usersInfoApiUrl + userId, jwt);
        UserDto userDto = (UserDto) microserviceRequest.sendGetRequest(userApiUrl + userId, jwt, UserDto.class);
        userDto.setExperience(userExperienceDtos);
        return userDto;
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable("userId") Long userId, @RequestHeader(value = "Authorization") String jwt) {
        microserviceRequest.sendDeleteRequest(userApiUrl + userId, jwt);
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto, @RequestHeader(value = "Authorization") String jwt) {
        return microserviceRequest.sendPostRequest(userApiUrl, jwt, userDto);
    }

}
