//package com.khomchenko.crud.controllers;
//
//import com.khomchenko.crud.configuration.MicroserviceRequest;
//import com.khomchenko.crud.dto.AuthenticationRequestDto;
//import com.khomchenko.crud.dto.AuthenticationsResponseDto;
//import com.khomchenko.crud.dto.UserDto;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//@Slf4j
//public class LoginController {
//
//    private final MicroserviceRequest microserviceRequest;
//
//    public LoginController(MicroserviceRequest microserviceRequest) {
//        this.microserviceRequest = microserviceRequest;
//    }
//
//
//    @PostMapping(path = "/login")
//    public AuthenticationsResponseDto login(@RequestBody AuthenticationRequestDto userDto) {
//        return microserviceRequest.sendPostRequestForLogin(userDto);
//    }
//}
