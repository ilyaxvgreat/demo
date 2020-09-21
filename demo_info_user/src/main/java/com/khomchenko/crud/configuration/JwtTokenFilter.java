package com.khomchenko.crud.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenFilter extends GenericFilterBean {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.header}")
    private String authorizationHeader;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        log.info("checking request");
        log.info(httpServletRequest.getHeader(authorizationHeader));
        if (httpServletRequest.getHeader(authorizationHeader) != null
                && validateToken(getTokenFromRequest(httpServletRequest))) {
            log.info("checking jwt with header");
            chain.doFilter(request, response);
        }else {
            log.info("without header");
            throw new JwtAuthenticationException("jwt token is expired or invalid", HttpStatus.UNAUTHORIZED);
        }
    }

    public String getTokenFromRequest(HttpServletRequest httpServletRequest){
        return httpServletRequest.getHeader(authorizationHeader);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException exception) {
            throw new JwtAuthenticationException("jwt token is expired or invalid", HttpStatus.UNAUTHORIZED);
        }
    }
}
