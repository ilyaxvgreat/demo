package com.khomchenko.auth.services;

import com.khomchenko.auth.model.Authority;
import com.khomchenko.auth.model.Role;
import com.khomchenko.auth.model.User;
import com.khomchenko.auth.services.exceptions.EmptyFieldException;
import com.khomchenko.auth.services.exceptions.UserAlreadyExistException;
import com.khomchenko.auth.services.exceptions.UserNotFoundException;
import com.khomchenko.auth.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userServiceImpl")
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findAll().stream().filter(user -> user.getUsername().equals(username))
                .findAny().orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User createUser(User user) {
        findAll().stream().filter(userFromDB -> user.getUsername().equals(userFromDB.getUsername()))
                .findAny().ifPresent(s -> {
            throw new UserAlreadyExistException();
        });
        user.setAuthorities(Set.of(roleService.getDefaultRole()));
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findAll()
                .stream().filter(user -> user.getId().equals(id)).findAny()
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findAll()
                .stream().filter(user -> user.getId().equals(id)).findAny()
                .orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        if ("".equals(user.getUsername())) {
            throw new EmptyFieldException();
        }
        User currentUser = userRepository.findAll()
                .stream().filter(userFromRepo -> userFromRepo.getId().equals(id)).findAny()
                .orElseThrow(UserNotFoundException::new);
        currentUser.setUsername(user.getUsername());
        return userRepository.save(currentUser);
    }
}
