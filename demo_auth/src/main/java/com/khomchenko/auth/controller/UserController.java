package com.khomchenko.auth.controller;

import com.khomchenko.auth.model.User;
import com.khomchenko.auth.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
