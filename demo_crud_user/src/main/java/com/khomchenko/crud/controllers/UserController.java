package com.khomchenko.crud.controllers;

import com.khomchenko.crud.dto.UserDto;
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
    private String apiUrl;

    @Value("${jwt.header}")
    private String authorizationHeader;

    private final WebClient.Builder webClientBuilder;

    public UserController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("/{id}")
    public Object getUserById(@PathVariable Long id, @RequestHeader(value = "Authorization") String jwt) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(apiUrl + "/" + id)
                    .header(authorizationHeader, jwt)
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            return new ResponseEntity<>("invalid name/password", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping
    public String test() {
        return "good";
    }


    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id, @RequestHeader(value = "Authorization") String jwt) {
        try {
            webClientBuilder.build()
                    .delete()
                    .uri("http://localhost:8081/users/" + id)
                    .header("Authorization", jwt)
                    .retrieve();
        } catch (WebClientResponseException e) {
            log.error("wrong", new Exception());
        }
    }

}
