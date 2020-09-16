package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/all")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping
    public User getOne(){
        User user = new User();
        user.setName("KWODFNDLSKNG");
        return user;
    }

}
