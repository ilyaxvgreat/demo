package com.khomchenko.auth.services;


import com.khomchenko.auth.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User createUser(User user);

    User getById(Long id);

    void deleteUser(Long id);

    User updateUser(Long id, User user);
}
