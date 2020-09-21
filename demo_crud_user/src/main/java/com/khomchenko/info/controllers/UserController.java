package com.khomchenko.info.controllers;

import com.khomchenko.info.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final WebClient.Builder webClientBuilder;

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public Object getUserById(@PathVariable Long id, @RequestHeader(value = "Authorization") String jwt) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/users/" + id)
                    .header("Authorization", jwt)
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            return new ResponseEntity<>("invalid name/password", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
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
