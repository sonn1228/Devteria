package com.sonnguyen.base.service;

import com.sonnguyen.base.dto.request.AuthRequest;
import com.sonnguyen.base.model.User;
import com.sonnguyen.base.repository.UserRepository;
import com.sonnguyen.base.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public boolean authenticate(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return user != null && encoder.matches(request.getPassword(), user.getPassword());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean register(AuthRequest request){
        return true;
    }
}
