package com.khomchenko.auth.services;

import com.khomchenko.auth.model.User;
import com.khomchenko.auth.services.exceptions.UserAlreadyExistException;
import com.khomchenko.auth.services.exceptions.UserNotFoundException;
import com.khomchenko.auth.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceImpl")
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

    @Override
    public User createUser(User user) {
        findAll().stream().filter(user1 -> user1.getName().equals(user.getName()))
                .findAny().orElseThrow(UserAlreadyExistException::new);
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }
}
