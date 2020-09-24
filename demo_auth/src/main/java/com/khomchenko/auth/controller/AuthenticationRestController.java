package com.khomchenko.auth.controller;

import com.khomchenko.auth.configuration.JwtTokenProvider;
import com.khomchenko.auth.dto.AuthenticationRequestDto;
import com.khomchenko.auth.model.User;
import com.khomchenko.auth.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String name = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
            User user = userRepository.findByUsername(requestDto.getUsername());
            String token = jwtTokenProvider.createToken(requestDto.getUsername(), user.getAuthorities());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", name);
            response.put("token", token);
            System.out.println(token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException authenticationException) {
            return new ResponseEntity<>("invalid name/password", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkToken() {
        log.info(" checking token ");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
