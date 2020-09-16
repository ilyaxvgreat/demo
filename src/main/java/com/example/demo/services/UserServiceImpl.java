package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findAll().stream().filter(user -> user.getName().equals(username))
                .findAny().orElseThrow(UserNotFoundException::new);
    }
}
