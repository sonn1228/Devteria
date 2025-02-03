package com.sonnguyen.base.service.Impl;

import com.sonnguyen.base.dto.response.AuthResponse;
import com.sonnguyen.base.exception.CommonException;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.repository.UserRepository;
import com.sonnguyen.base.service.AuthService;
import com.sonnguyen.base.utils.JwtService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByUsername(username);

        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    @Override
    public void register(String username, String password) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            throw new CommonException("username exist", HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public boolean introspect(String token) {
        boolean isValid = false;
        String message = "Token is invalid";
        try {
            jwtService.extractUsername(token);
            if (!jwtService.isTokenExpired(token)) {
                isValid = true;
                message = "Token is valid";
            } else {
                message = "Token is expired";
            }
        } catch (JwtException e) {
            message = "Token is invalid: " + e.getMessage();
        }
        return isValid;
    }
}
