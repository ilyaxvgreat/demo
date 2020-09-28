package com.khomchenko.crud.configuration;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

@Component
@Slf4j
public class JwtTokenFilter extends GenericFilterBean {

    @Value("${jwt.secret}")
    private transient String secretKey;

    @Value("${jwt.header}")
    private transient String authorizationHeader;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        try {
            if (httpServletRequest.getHeader(authorizationHeader) != null
                    && validateToken(token)) {
                log.info("checking jwt with header");
                chain.doFilter(request, response);
            }
        } catch (JwtAuthenticationException exception) {
            log.info("without header");
            throw new JwtAuthenticationException("jwt token is expired or invalid", HttpStatus.UNAUTHORIZED);
        }
//        chain.doFilter(request, response);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody();
            return true;
        } catch (JwtException | IllegalArgumentException exception) {
            throw new JwtAuthenticationException("jwt token is expired or invalid", HttpStatus.UNAUTHORIZED);
        }
    }
}
